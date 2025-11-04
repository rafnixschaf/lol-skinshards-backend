package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sascha.skinshard_virtualizer.model.Champion;
import tech.sascha.skinshard_virtualizer.model.external.LoLChampion;
import tech.sascha.skinshard_virtualizer.repository.ChampionRepository;
import tech.sascha.skinshard_virtualizer.util.DDragonNameNormalizer;

import java.util.Objects;

@Service
public class ChampionService {

    @Autowired
    ChampionRepository championRepository;

    @Transactional
    public Champion getOrCreate(String name) {
        return championRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> championRepository.save(new Champion(name)));
    }

    @Transactional
    public void createOrUpdate(LoLChampion champion) {
        Champion champ =  championRepository.findByNameIgnoreCase(DDragonNameNormalizer.normalize(champion.getName()) )
                                 .orElseGet(() -> championRepository.save(new Champion(champion)));

        if(Objects.isNull(champ.getId())) {
            champ.setChampionId(champion.getId());
            championRepository.save(champ);
        }
    }
}
