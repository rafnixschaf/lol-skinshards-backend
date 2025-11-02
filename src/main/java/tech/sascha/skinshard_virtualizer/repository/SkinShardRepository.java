package tech.sascha.skinshard_virtualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sascha.skinshard_virtualizer.model.SkinShard;

import java.util.Optional;

public interface SkinShardRepository extends JpaRepository<SkinShard, Long> {

    Optional<SkinShard> findByLootId(String lootId);
}
