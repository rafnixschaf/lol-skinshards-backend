package tech.sascha.skinshard_virtualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sascha.skinshard_virtualizer.model.Skin;

public interface SummonerSkinsRepository extends JpaRepository<Skin,Long> {
}
