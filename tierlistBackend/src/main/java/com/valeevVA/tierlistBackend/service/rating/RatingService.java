package com.valeevVA.tierlistBackend.service.rating;

import com.valeevVA.tierlistBackend.model.rating.Rating;

import java.util.List;

public interface RatingService {
    public Rating saveRating(Rating rating);
    public List<Rating> getRating();
}
