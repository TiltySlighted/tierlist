package com.valeevVA.tierlistBackend.controller.tierlist;

import com.valeevVA.tierlistBackend.dto.SimplifiedImageDTO;
import com.valeevVA.tierlistBackend.dto.SimplifiedTierDTO;
import com.valeevVA.tierlistBackend.dto.TierlistContentDTO;
import com.valeevVA.tierlistBackend.dto.TierlistDTO;
import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.service.image.ImageService;
import com.valeevVA.tierlistBackend.service.tier.TierService;
import com.valeevVA.tierlistBackend.service.tierlist.TierlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tierlist")
@CrossOrigin
public class TierlistController {
    @Autowired
    private TierlistService tierlistService;

    @Autowired
    private TierService tierService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/create") //успешно проверено в Postman
    public ResponseEntity<String> createTierlist(@RequestBody Tierlist tierlist) {
        try {
            // Сохраняем тирлист
            Tierlist savedTierlist = tierlistService.saveTierlist(tierlist);

            // Создаем автоматически UNRATED tier
            Tier unratedTier = new Tier();
            unratedTier.setTiername("UNRATED");
            unratedTier.setColor("#FFFFFF");
            unratedTier.setPos(0);
            unratedTier.setTierlist(savedTierlist);

            tierService.saveTier(unratedTier);

            return ResponseEntity.ok("Тирлист успешно создан с UNRATED тиром");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка создания тирлиста: " + e.getMessage());
        }
    }

    @PostMapping("/{tierlistId}/add-tier") //успешно проверено в Postman
    public ResponseEntity<String> addTierToTierlist(@PathVariable int tierlistId, @RequestBody Tier tier) {
        try {
            // Получаем тирлист (упрощенная версия, в реальности нужно добавить проверки)
            Tierlist tierlist = tierlistService.getTierlist().stream()
                    .filter(t -> t.getId_tierlist() == tierlistId)
                    .findFirst()
                    .orElse(null);

            if (tierlist == null) {
                return ResponseEntity.notFound().build();
            }

            tier.setTierlist(tierlist);
            tierService.saveTier(tier);

            return ResponseEntity.ok("Тир успешно добавлен в тирлист");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления тира: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}") // успешно проверено в Postman
    public ResponseEntity<List<TierlistDTO>> getUserTierlists(@PathVariable int userId) {
        List<TierlistDTO> tierlists = tierlistService.getUserTierlists(userId);
        return ResponseEntity.ok(tierlists);
    }

    @GetMapping("/public") // Теперь с пагинацией, успешно проверено в Postman
    public ResponseEntity<Page<TierlistDTO>> getPublicTierlists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TierlistDTO> publicTierlists = tierlistService.getPublicTierlists(pageable);
        return ResponseEntity.ok(publicTierlists);
    }

    @GetMapping("/{tierlistId}/content") // успешно проверено в Postman
    public ResponseEntity<?> getTierlistContent(@PathVariable int tierlistId) {
        try {
            // Получаем и преобразуем тиры
            List<SimplifiedTierDTO> tiers = tierService.getTiersByTierlistId(tierlistId).stream()
                    .map(t -> new SimplifiedTierDTO(
                            t.getId_tier(),
                            t.getColor(),
                            t.getTiername(),
                            t.getPos()))
                    .collect(Collectors.toList());

            // Получаем и преобразуем изображения
            List<SimplifiedImageDTO> images = imageService.getImagesByTierlistId(tierlistId).stream()
                    .map(i -> new SimplifiedImageDTO(
                            i.getId_image(),
                            i.getBase64()))
                    .collect(Collectors.toList());

            TierlistContentDTO response = new TierlistContentDTO(tiers, images);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}