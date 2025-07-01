package com.valeevVA.tierlistBackend.controller.rating;

import com.valeevVA.tierlistBackend.dto.AddRatingDTO;
import com.valeevVA.tierlistBackend.model.average.Average;
import com.valeevVA.tierlistBackend.model.image.Image;
import com.valeevVA.tierlistBackend.model.rating.Rating;
import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.service.image.ImageService;
import com.valeevVA.tierlistBackend.service.rating.RatingService;
import com.valeevVA.tierlistBackend.service.tier.TierService;
import com.valeevVA.tierlistBackend.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/add") //успешно проверено в Postman
    public ResponseEntity<String> addRating(
            @RequestBody AddRatingDTO request) { // Принимаем DTO в теле запроса
        try {
            // Получаем данные из DTO
            int userId = request.getUserId();
            int tierId = request.getTierId();
            int imageId = request.getImageId();

            // Остальная логика (как в вашем коде)
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

            Rating rating = new Rating();
            rating.setUser(user);
            rating.setTier(tier);
            rating.setImage(image);

            ratingService.saveRating(rating);

            return ResponseEntity.ok("Оценка успешно добавлена");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Не найдена сущность: " + e.getMessage());

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка базы данных");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Некорректные данные: " + e.getMessage());
        }
    }

    @GetMapping("/calculate-averages") //успешно проверено в Postman
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