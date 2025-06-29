package com.valeevVA.tierlistBackend.repository.image;

import com.valeevVA.tierlistBackend.model.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
}
