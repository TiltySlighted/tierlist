//модель оценки
package com.valeevVA.tierlistBackend.model.rating;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.model.image.Image;
import jakarta.persistence.*;

@Entity
@Table(schema = "public")
public class Rating {

    //ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rating;

    //СВЯЗИ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user; //связь один-ко-многим пользователь --> оценка
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tier", referencedColumnName = "id_tier")
    private Tier tier; //связь один-ко-многим тир (tier) --> оценка
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", referencedColumnName = "id_image")
    private Image image; //связь один-ко-многим картинка --> оценка

    //КОНСТРУКТОРЫ
    public Rating() {}
    public Rating(int id_rating, User user, Tier tier, Image image) {
        this.id_rating = id_rating;
        this.user = user;
        this.tier = tier;
        this.image = image;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_rating() {
        return id_rating;
    }
    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Tier getTier() {
        return tier;
    }
    public void setTier(Tier tier) {
        this.tier = tier;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
