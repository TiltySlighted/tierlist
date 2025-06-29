package com.valeevVA.tierlistBackend.controller.rating;

import com.valeevVA.tierlistBackend.model.average.Average;
import com.valeevVA.tierlistBackend.model.image.Image;
import com.valeevVA.tierlistBackend.model.rating.Rating;
import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.service.image.ImageService;
import com.valeevVA.tierlistBackend.service.rating.RatingService;
import com.valeevVA.tierlistBackend.service.tier.TierService;
import com.valeevVA.tierlistBackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TierService tierService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/add")
    public ResponseEntity<String> addRating(
            @RequestParam int userId,
            @RequestParam int tierId,
            @RequestParam int imageId) {
        try {
            // Получаем сущности
            User user = userService.getUser().stream()
                    .filter(u -> u.getId_user() == userId)
                    .findFirst()
                    .orElse(null);

            Tier tier = tierService.getTier().stream()
                    .filter(t -> t.getId_tier() == tierId)
                    .findFirst()
                    .orElse(null);

            Image image = imageService.getImage().stream()
                    .filter(i -> i.getId_image() == imageId)
                    .findFirst()
                    .orElse(null);

            if (user == null || tier == null || image == null) {
                return ResponseEntity.notFound().build();
            }

            // Создаем и сохраняем оценку
            Rating rating = new Rating();
            rating.setUser(user);
            rating.setTier(tier);
            rating.setImage(image);

            ratingService.saveRating(rating);

            return ResponseEntity.ok("Оценка успешно добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления оценки: " + e.getMessage());
        }
    }

    @GetMapping("/calculate-averages")
    public ResponseEntity<String> calculateAverageRatings() {
        try {
            List<Image> images = imageService.getImage();

            for (Image image : images) {
                List<Rating> ratings = image.getRatings();

                if (!ratings.isEmpty()) {
                    // Вычисляем среднюю позицию
                    double averagePos = ratings.stream()
                            .mapToInt(r -> r.getTier().getPos())
                            .average()
                            .orElse(0.0);

                    int roundedPos = (int) Math.round(averagePos);

                    // Находим соответствующий тир
                    Tier averageTier = tierService.getTier().stream()
                            .filter(t -> t.getPos() == roundedPos &&
                                    t.getTierlist().getId_tierlist() ==
                                            image.getTierlist().getId_tierlist())
                            .findFirst()
                            .orElse(null);

                    if (averageTier != null) {
                        // Обновляем среднюю оценку
                        Average average = image.getAverage();
                        if (average == null) {
                            average = new Average();
                            average.setImage(image);
                        }
                        average.setTier(averageTier);

                        // В реальности нужно добавить сервис для сохранения Average
                        image.setAverage(average);
                        imageService.saveImage(image);
                    }
                }
            }

            return ResponseEntity.ok("Средние оценки успешно пересчитаны");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка вычисления средних оценок: " + e.getMessage());
        }
    }
}