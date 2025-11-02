package tech.sascha.skinshard_virtualizer.service.mapper;

import org.springframework.stereotype.Service;
import tech.sascha.skinshard_virtualizer.dto.SkinShardDto;
import tech.sascha.skinshard_virtualizer.model.SkinShard;

@Service
public class SkinShardMapper {

    public SkinShardDto toDto(SkinShard entity) {
        SkinShardDto dto = new SkinShardDto();
        dto.setId(entity.getId());
        dto.setItemName(entity.getItemDesc());
        dto.setRarity(entity.getRarity());
        dto.setDisenchantValue(entity.getDisenchantValue());
        dto.setUpgradeEssenceValue(entity.getUpgradeEssenceValue());
        dto.setValue(entity.getValue());
        dto.setWanted(entity.getWanted() != null ? entity.getWanted() : false);
        if (entity.getChampion() != null) {
            dto.setChampionName(entity.getChampion().getName());
        } else {
            dto.setChampionName("Unknown");
        }

        if (entity.getLocalImagePath() != null) {
            String fileName = entity.getLocalImagePath()
                                    .replace("\\", "/")
                                    .substring(entity.getLocalImagePath().replace("\\", "/").lastIndexOf("/") + 1);
            dto.setImageUrl("/images/" + fileName);
        } else if (entity.getDdragonUrl() != null) {
            dto.setImageUrl(entity.getDdragonUrl());
        }

        return dto;
    }
}
