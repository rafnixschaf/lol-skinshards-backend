package tech.sascha.skinshard_virtualizer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.sascha.skinshard_virtualizer.model.external.LoLSkinMinimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skin extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champion_id")
    Champion champion;

    Boolean disabled;

    Boolean isBase;

    Boolean owned;

    public Skin(Champion champion, LoLSkinMinimal skinMinimal) {
        this.champion = champion;
        this.disabled = skinMinimal.getDisabled();
        this.isBase = skinMinimal.getIsBase();
        this.owned = skinMinimal.getOwnership().getOwned();
    }

}
