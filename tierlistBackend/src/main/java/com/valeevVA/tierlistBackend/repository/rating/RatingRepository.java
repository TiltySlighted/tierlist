package com.valeevVA.tierlistBackend.repository.rating;

import com.valeevVA.tierlistBackend.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
