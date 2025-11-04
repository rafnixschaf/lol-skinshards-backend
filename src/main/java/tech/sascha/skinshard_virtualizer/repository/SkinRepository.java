package tech.sascha.skinshard_virtualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.sascha.skinshard_virtualizer.model.Skin;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {

    @Query("select distinct s.champion.id from Skin s where s.owned = true")
    List<Long> findChampionIdsWithOwnedSkins();

    @Query("""
            select distinct s.champion.id
            from Skin s
            where s.owned = true
            and (s.isBase = false or s.isBase is null)
            """)
    List<Long> findChampionIdsWithAnyOwnedSkin();
}
