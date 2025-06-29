package com.valeevVA.tierlistBackend.repository.tier;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<Tier,Integer> {
}
