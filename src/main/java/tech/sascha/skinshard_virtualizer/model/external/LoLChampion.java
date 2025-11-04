package tech.sascha.skinshard_virtualizer.model.external;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LoLChampion {

    private boolean active;
    private String alias;
    private String banVoPath;
    private String baseLoadScreenPath;
    private String baseSplashPath;
    private boolean botEnabled;
    private String chooseVoPath;
    private List<String> disabledQueues;
    private boolean freeToPlay;
    private Long id;
    private boolean isVisibleInClient;
    private String name;
    private LoLSkinMinimal.OwnerShip ownership;
    private long purchased;
    private boolean rankedPlayEnabled;
    private List<String> roles;
    private String squarePortraitPath;
    private String stingerSfxPath;
    private String title;
}
