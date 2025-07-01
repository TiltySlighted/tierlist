//модель картинки
package com.valeevVA.tierlistBackend.model.image;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.model.rating.Rating;
import com.valeevVA.tierlistBackend.model.average.Average;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public")
public class Image {

    //ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_image;
    @Column(columnDefinition = "TEXT") // Используем TEXT вместо VARCHAR
    private String base64;

    //СВЯЗИ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tierlist", referencedColumnName = "id_tierlist")
    private Tierlist tierlist; //связь один-ко-многим тирлист --> картинка
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>(); //связь один-ко-многим картинка --> оценка
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private Average average; //связь один-к-одному картинка --> средняя_оценка

    //КОНСТРУКТОРЫ
    public Image() {}
    public Image (int id_image, String base64, Tierlist tierlist) {
        this.id_image = id_image;
        this.base64 = base64;
        this.tierlist = tierlist;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_image() {
        return id_image;
    }
    public void setId_image(int id_image) {
        this.id_image = id_image;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
    public Tierlist getTierlist() {
        return tierlist;
    }
    public void setTierlist(Tierlist tierlist) {
        this.tierlist = tierlist;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    public Average getAverage() {
        return average;
    }
    public void setAverage(Average average) {
        this.average = average;
    }
}
