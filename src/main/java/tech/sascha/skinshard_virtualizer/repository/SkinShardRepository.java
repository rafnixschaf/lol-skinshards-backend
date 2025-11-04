package tech.sascha.skinshard_virtualizer.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.sascha.skinshard_virtualizer.model.SkinShard;

import java.util.List;
import java.util.Optional;

public interface SkinShardRepository extends JpaRepository<SkinShard, Long> {

    Optional<SkinShard> findByLootId(String lootId);

    @EntityGraph(attributePaths = "champion")
    @Query("select ss from SkinShard ss")
    List<SkinShard> findAllWithChampion();
}
