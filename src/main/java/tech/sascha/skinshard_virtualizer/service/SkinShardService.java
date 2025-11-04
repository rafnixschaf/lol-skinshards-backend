package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sascha.skinshard_virtualizer.dto.SkinShardDto;
import tech.sascha.skinshard_virtualizer.model.BaseEntity;
import tech.sascha.skinshard_virtualizer.model.SkinShard;
import tech.sascha.skinshard_virtualizer.repository.SkinRepository;
import tech.sascha.skinshard_virtualizer.repository.SkinShardRepository;
import tech.sascha.skinshard_virtualizer.service.mapper.SkinShardMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkinShardService {
    @Autowired
    SkinShardRepository skinShardRepository;

    @Autowired
    SkinRepository skinRepository;

    @Autowired
    SkinShardMapper skinShardMapper;

    public SkinShard setWanted(Long id, Boolean isWanted) {
        SkinShard skinShard = skinShardRepository.findById(id)
                                                 .orElseThrow(() -> new RuntimeException("Skin not found"));

        skinShard.setWanted(isWanted);
        return skinShardRepository.save(skinShard);

    }

    public SkinShard setReroll(Long id, Boolean isWanted) {
        SkinShard skinShard = skinShardRepository.findById(id)
                                                 .orElseThrow(() -> new RuntimeException("Skin not found"));

        skinShard.setReroll(isWanted);
        return skinShardRepository.save(skinShard);

    }

    public SkinShard setSell(Long id, Boolean isWanted) {
        SkinShard skinShard = skinShardRepository.findById(id)
                                                 .orElseThrow(() -> new RuntimeException("Skin not found"));

        skinShard.setSell(isWanted);
        return skinShardRepository.save(skinShard);

    }

    public List<SkinShardDto> getAll() {

        List<SkinShard> shards = skinShardRepository.findAllWithChampion();

        var championIds = skinRepository.findChampionIdsWithAnyOwnedSkin();
        var ownedSet = new java.util.HashSet<>(championIds);

        return shards.stream()
                     .map(ss -> {
                         var dto = skinShardMapper.toDto(ss);
                         Long champId = (ss.getChampion() != null) ? ss.getChampion().getId() : null;
                         dto.setOwnsAnySKinForChampion(champId != null && ownedSet.contains(champId));
                         return dto;
                     })
                     .toList();
    }
}
