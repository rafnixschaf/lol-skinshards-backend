package tech.sascha.skinshard_virtualizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.sascha.skinshard_virtualizer.service.ChampionImportService;
import tech.sascha.skinshard_virtualizer.service.SkinShardImportService;
import tech.sascha.skinshard_virtualizer.service.SummonerImportSkinsService;

@Component
public class ImportRunner implements CommandLineRunner {

    @Autowired
    SkinShardImportService skinShardImportService;

    @Autowired
    ChampionImportService championImportService;

    @Autowired
    SummonerImportSkinsService summonerImportSkinsService;

    @Override
    public void run(String ...args) throws Exception {
        championImportService.importChampions();
        summonerImportSkinsService.importSkins();
        skinShardImportService.importSkins();
    }
}
