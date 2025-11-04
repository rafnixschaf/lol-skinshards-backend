package tech.sascha.skinshard_virtualizer.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sascha.skinshard_virtualizer.LcuClient;
import tech.sascha.skinshard_virtualizer.model.Champion;
import tech.sascha.skinshard_virtualizer.model.SkinShard;
import tech.sascha.skinshard_virtualizer.repository.SkinShardRepository;
import tech.sascha.skinshard_virtualizer.util.DDragonNameNormalizer;
import tech.sascha.skinshard_virtualizer.util.SplashPathParser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SkinShardImportService {

    private final LcuClient lcuClient;
    private final ChampionService championService;
    private final SkinShardRepository skinShardRepository;
    private final ImageDownloadService imageDownloader;
    private final String ddragonBase;

    public SkinShardImportService(LcuClient lcuClient,
                                  ChampionService championService,
                                  SkinShardRepository skinShardRepository,
                                  ImageDownloadService imageDownloader,
                                  @Value("${lol.ddragon-base}") String ddragonBase) {
        this.lcuClient = lcuClient;
        this.championService = championService;
        this.skinShardRepository = skinShardRepository;
        this.imageDownloader = imageDownloader;
        this.ddragonBase = ddragonBase;
    }

    @Transactional
    public void importSkins() {
        List<Map> loot = lcuClient.getPlayerLoot();
        for (Map<String, Object> entry : loot) {
            String displayCategories = (String) entry.get("displayCategories");
            String type = (String) entry.get("type");
            if (!isSkin(displayCategories, type)) {
                //continue;
            }

            String lootId = (String) entry.get("lootId");
            Optional<SkinShard> existing = skinShardRepository.findByLootId(lootId);
            SkinShard skinShard = existing.orElseGet(SkinShard::new);

            skinShard.setLootId(lootId);
            skinShard.setLootName((String) entry.get("lootName"));
            skinShard.setItemDesc((String) entry.get("itemDesc"));
            skinShard.setRarity((String) entry.get("rarity"));
            skinShard.setDisenchantValue(toInt(entry.get("disenchantValue")));
            skinShard.setUpgradeEssenceValue(toInt(entry.get("upgradeEssenceValue")));
            skinShard.setValue(toInt(entry.get("value")));
            skinShard.setCount(toInt(entry.get("count")));
            skinShard.setAsset((String) entry.get("asset"));
            String splashPath = (String) entry.get("splashPath");
            skinShard.setSplashPath(splashPath);

            var parsed = SplashPathParser.parse(splashPath);
            Champion champion = null;
            Integer skinNum = null;
            if (parsed.isPresent()) {
                var p = parsed.get();
                String championName = DDragonNameNormalizer.normalize(p.champion());
                champion = championService.getOrCreate(championName);
                skinNum = p.skinNumber();
            }

            skinShard.setChampion(champion);
            skinShard.setSkinNumber(skinNum);

            String ddragonUrl = null;
            if (champion != null && skinNum != null) {
                ddragonUrl = ddragonBase + "/" + champion.getName() + "_" + skinNum + ".jpg";
                skinShard.setDdragonUrl(ddragonUrl);


                String fileName = champion.getName() + "_" + skinNum + ".jpg";
                String localPath = imageDownloader.download(ddragonUrl, fileName);
                skinShard.setLocalImagePath(localPath);
            }

            skinShardRepository.save(skinShard);
        }
    }

    private boolean isSkin(String displayCategories, String type) {
        if (displayCategories != null && displayCategories.contains("SKIN")) return true;
        if (type != null && type.toUpperCase().contains("SKIN")) return true;
        return false;
    }

    private Integer toInt(Object o) {
        if (o == null) return null;
        if (o instanceof Integer i) return i;
        if (o instanceof Number n) return n.intValue();
        return Integer.parseInt(o.toString());
    }
}
