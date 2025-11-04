package tech.sascha.skinshard_virtualizer.model.external;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoLSkinMinimal {
    Long championId;

    Boolean disabled;

    Boolean isBase;

    OwnerShip ownership;


    @Getter @Setter
    public static class OwnerShip {
        Boolean owned;
    }
}
