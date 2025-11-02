package tech.sascha.skinshard_virtualizer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sascha.skinshard_virtualizer.dto.SkinShardDto;
import tech.sascha.skinshard_virtualizer.model.SkinShard;
import tech.sascha.skinshard_virtualizer.repository.SkinShardRepository;
import tech.sascha.skinshard_virtualizer.service.mapper.SkinShardMapper;

import java.util.List;

@RestController
@RequestMapping("/skins")
@CrossOrigin(origins = "http://localhost:4200")
public class SkinShardController {

    @Autowired
    SkinShardRepository skinShardRepository;

    @Autowired
    SkinShardMapper skinShardMapper;

    @GetMapping
    public List<SkinShardDto> getAll() {
        return skinShardRepository.findAll()
                .stream()
                .map(skinShardMapper::toDto)
                .toList();
    }

    @PatchMapping("/{id}/wanted")
    public ResponseEntity<SkinShardDto> setWanted(@PathVariable Long id,
                                                  @RequestParam boolean value) {
        SkinShard skinShard = skinShardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skin not found"));

        skinShard.setWanted(value);
        skinShard = skinShardRepository.save(skinShard);

        return ResponseEntity.ok(skinShardMapper.toDto(skinShard));
    }
}
