package com.valeevVA.tierlistBackend.repository.average;

import com.valeevVA.tierlistBackend.model.average.Average;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageRepository extends JpaRepository<Average, Integer> {
}
