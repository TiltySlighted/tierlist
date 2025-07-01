package com.valeevVA.tierlistBackend.dto;

import java.util.List;

public class TierlistContentDTO {
    private List<SimplifiedTierDTO> tiers;
    private List<SimplifiedImageDTO> images;

    // Конструктор и геттеры
    public TierlistContentDTO(List<SimplifiedTierDTO> tiers, List<SimplifiedImageDTO> images) {
        this.tiers = tiers;
        this.images = images;
    }
    public List<SimplifiedImageDTO> getImages() {
        return images;
    }
    public void setImages(List<SimplifiedImageDTO> images) {
        this.images = images;
    }
    public List<SimplifiedTierDTO> getTiers() {
        return tiers;
    }
    public void setTiers(List<SimplifiedTierDTO> tiers) {
        this.tiers = tiers;
    }
}
