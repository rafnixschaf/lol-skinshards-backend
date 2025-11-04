package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sascha.skinshard_virtualizer.LcuClient;
import tech.sascha.skinshard_virtualizer.model.external.LoLChampion;

import java.util.List;

@Service
public class ChampionImportService {
    @Autowired
    LcuClient lcuClient;

    @Autowired
    ChampionService championService;

    public void importChampions() {
        List<LoLChampion> champions = lcuClient.getPlayerChampions();
        champions.forEach(championService::createOrUpdate);
    }
}
