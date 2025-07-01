package com.valeevVA.tierlistBackend.controller.image;

import com.valeevVA.tierlistBackend.dto.ImageUploadRequestDTO;
import com.valeevVA.tierlistBackend.model.image.Image;
import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.service.image.ImageService;
import com.valeevVA.tierlistBackend.service.tierlist.TierlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TierlistService tierlistService;

    @PostMapping("/upload") //успешно проверено в Postman
    public ResponseEntity<String> uploadImage(
            @ModelAttribute ImageUploadRequestDTO request) { // Принимаем DTO
        try {
            MultipartFile file = request.getFile();
            int tierlistId = request.getTierlistId();

            // Остальная логика (как в вашем коде)
            Tierlist tierlist = tierlistService.getTierlist().stream()
                    .filter(t -> t.getId_tierlist() == tierlistId)
                    .findFirst()
                    .orElse(null);

            if (tierlist == null) {
                return ResponseEntity.notFound().build();
            }

            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            Image image = new Image();
            image.setBase64(base64Image);
            image.setTierlist(tierlist);
            imageService.saveImage(image);

            return ResponseEntity.ok("Изображение успешно загружено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка загрузки изображения: " + e.getMessage());
        }
    }
}