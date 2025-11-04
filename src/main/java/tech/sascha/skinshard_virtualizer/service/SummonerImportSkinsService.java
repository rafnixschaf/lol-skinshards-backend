package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sascha.skinshard_virtualizer.LcuClient;
import tech.sascha.skinshard_virtualizer.model.Champion;
import tech.sascha.skinshard_virtualizer.model.Skin;
import tech.sascha.skinshard_virtualizer.model.external.LoLSkinMinimal;
import tech.sascha.skinshard_virtualizer.model.external.LoLSummoner;
import tech.sascha.skinshard_virtualizer.repository.ChampionRepository;
import tech.sascha.skinshard_virtualizer.repository.SummonerSkinsRepository;

import java.util.List;

@Service
public class SummonerImportSkinsService {

    @Autowired
    SummonerSkinsRepository summonerSkinsRepository;

    @Autowired
    ChampionRepository championRepository;

    @Autowired
    LcuClient lcuClient;

    @Transactional
    public void importSkins() {

        LoLSummoner summoner = lcuClient.getCurrentSummoner();
        List<LoLSkinMinimal> summonerSkins = lcuClient.getPlayerSkins(summoner.getSummonerId());

        summonerSkins
                .stream()
                .filter(skinMinimal -> Boolean.FALSE.equals(skinMinimal.getDisabled()))
                .filter(skinMinimal -> Boolean.FALSE.equals(skinMinimal.getIsBase()))
                .filter(skinMinimal -> Boolean.TRUE.equals(skinMinimal
                                                                   .getOwnership()
                                                                   .getOwned()))
                .map(skin -> {

                    Champion champion = championRepository
                            .findByChampionId(skin.getChampionId())
                            .orElseThrow(
                                    () -> new RuntimeException("no champion found")
                                        );
                    Skin newSkin = new Skin(champion, skin);
                    return summonerSkinsRepository.save(newSkin);
                }).toList();
        ;

    }
}
