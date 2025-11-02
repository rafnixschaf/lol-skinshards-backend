package tech.sascha.skinshard_virtualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sascha.skinshard_virtualizer.model.Champion;

import java.util.Optional;

public interface ChampionRepository extends JpaRepository<Champion, Long> {
    Optional<Champion> findByNameIgnoreCase(String name);
}
