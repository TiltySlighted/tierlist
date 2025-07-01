package com.valeevVA.tierlistBackend.repository.tier;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierRepository extends JpaRepository<Tier,Integer> {
    @Query("SELECT t FROM Tier t WHERE t.tierlist.id_tierlist = :tierlistId ORDER BY t.pos")
    List<Tier> findByTierlistId(@Param("tierlistId") int tierlistId);
}
