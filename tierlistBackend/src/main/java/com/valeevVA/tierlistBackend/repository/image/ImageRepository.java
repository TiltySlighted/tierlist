package com.valeevVA.tierlistBackend.repository.image;

import com.valeevVA.tierlistBackend.model.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
    @Query("SELECT i FROM Image i WHERE i.tierlist.id_tierlist = :tierlistId")
    List<Image> findByTierlistId(@Param("tierlistId") int tierlistId);
}
