package com.valeevVA.tierlistBackend.service.image;

import com.valeevVA.tierlistBackend.model.image.Image;

import java.util.List;

public interface ImageService {
    public Image saveImage(Image tierlist);
    public List<Image> getImage();
    List<Image> getImagesByTierlistId(int tierlistId);
}
