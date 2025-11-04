package tech.sascha.skinshard_virtualizer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sascha.skinshard_virtualizer.dto.SkinShardDto;
import tech.sascha.skinshard_virtualizer.model.SkinShard;
import tech.sascha.skinshard_virtualizer.repository.SkinShardRepository;
import tech.sascha.skinshard_virtualizer.service.SkinShardService;
import tech.sascha.skinshard_virtualizer.service.mapper.SkinShardMapper;

import java.util.List;

@RestController
@RequestMapping("/skins")
@CrossOrigin(origins = "http://localhost:4200")
public class SkinShardController {

    @Autowired
    SkinShardService skinShardService;

    @Autowired
    SkinShardMapper skinShardMapper;

    @GetMapping
    public List<SkinShardDto> getAll() {
        return skinShardService.getAll();
    }

    @PatchMapping("/{id}/wanted")
    public ResponseEntity<SkinShardDto> setWanted(@PathVariable Long id,
                                                  @RequestParam boolean value) {
        return ResponseEntity.ok(
                skinShardMapper
                        .toDto(skinShardService
                                       .setWanted(id, value)));
    }
}
