package tech.sascha.skinshard_virtualizer.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Summoner extends BaseEntity {
    private Long accountId;

    private String gameName;

    private Long summonerId;

    private Long summonerLevel;
}
