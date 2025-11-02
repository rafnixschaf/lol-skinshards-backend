package tech.sascha.skinshard_virtualizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Champion extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String riotKey;

    public Champion(String name) {
        this.name = name;
    }


}
