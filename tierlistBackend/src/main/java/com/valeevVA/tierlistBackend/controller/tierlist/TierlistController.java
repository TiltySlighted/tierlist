package com.valeevVA.tierlistBackend.controller.tierlist;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.service.tier.TierService;
import com.valeevVA.tierlistBackend.service.tierlist.TierlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tierlist")
@CrossOrigin
public class TierlistController {
    @Autowired
    private TierlistService tierlistService;

    @Autowired
    private TierService tierService;

    @PostMapping("/create")
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

    @PostMapping("/{tierlistId}/add-tier")
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Tierlist>> getUserTierlists(@PathVariable int userId) {
        List<Tierlist> tierlists = tierlistService.getTierlist().stream()
                .filter(t -> t.getUser().getId_user() == userId)
                .toList();

        return ResponseEntity.ok(tierlists);
    }
}