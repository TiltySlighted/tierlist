package com.valeevVA.tierlistBackend.service.image;

import com.valeevVA.tierlistBackend.model.image.Image;
import com.valeevVA.tierlistBackend.repository.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> getImage() {
        return imageRepository.findAll();
    }
}
