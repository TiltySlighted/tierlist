package com.valeevVA.tierlistBackend.repository.tierlist;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierlistRepository extends JpaRepository<Tierlist,Integer> {
}
