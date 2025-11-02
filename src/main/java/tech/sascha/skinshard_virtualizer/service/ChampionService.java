package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sascha.skinshard_virtualizer.model.Champion;
import tech.sascha.skinshard_virtualizer.repository.ChampionRepository;

@Service
public class ChampionService {

    @Autowired
    ChampionRepository championRepository;

    @Transactional
    public Champion getOrCreate(String name) {
        return championRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> championRepository.save(new Champion(name)));
    }
}
