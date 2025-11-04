package tech.sascha.skinshard_virtualizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.sascha.skinshard_virtualizer.model.external.LoLChampion;
import tech.sascha.skinshard_virtualizer.util.DDragonNameNormalizer;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Champion extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String riotKey;

    @Column(name = "champion_id", unique = true)
    private Long championId;

    public Champion(String name) {
        this.name = name;
    }

    public Champion(LoLChampion champion) {
        this.name = DDragonNameNormalizer.normalize(champion.getName());
        this.championId = champion.getId();
    }


}
