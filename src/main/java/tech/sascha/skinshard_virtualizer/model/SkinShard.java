package tech.sascha.skinshard_virtualizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skin_shard")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SkinShard extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String lootId;

    private String lootName;

    private String itemDesc;

    private String rarity;

    private Integer disenchantValue;

    private Integer upgradeEssenceValue;

    private Integer value;

    private Integer count;

    private String localImagePath;

    private String splashPath;

    private String ddragonUrl;

    private Integer skinNumber;

    private Boolean wanted = false;

    private Boolean sell = false;

    private Boolean reroll = false;

    private String asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champion_id")
    private Champion champion;

}
