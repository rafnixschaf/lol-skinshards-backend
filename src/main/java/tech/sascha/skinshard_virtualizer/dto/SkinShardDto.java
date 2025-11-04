package tech.sascha.skinshard_virtualizer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkinShardDto {
    private Long id;
    private String championName;
    private String itemName;
    private String rarity;
    private Integer disenchantValue;
    private Integer upgradeEssenceValue;
    private Integer value;
    private Boolean wanted;
    private String imageUrl;
    private Boolean ownsAnySKinForChampion;
}
