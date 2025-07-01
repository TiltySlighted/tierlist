package com.valeevVA.tierlistBackend.model.average;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.model.image.Image;
import jakarta.persistence.*;

@Entity
@Table(schema = "public")
public class Average {
    //ПОЛЯ
    @Id
    @Column(name = "id_image") // PK совпадает с FK
    private int id_image; //первичный ключ и внешний ключ одновременно

    //СВЯЗИ
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId //id_image берется из Image
    @JoinColumn(name = "id_image", referencedColumnName = "id_image")
    private Image image; //связь один-к-одному картинка --> средняя_оценка

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tier", referencedColumnName = "id_tier")
    private Tier tier; //связь один-ко-многим картинка --> средняя_оценка

    //КОНСТРУКТОРЫ
    public Average() {}
    public Average(Image image, Tier tier) {
        this.image = image;
        this.tier = tier;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_image() {
        return id_image;
    }
    public void setId_image(int id_image) {
        this.id_image = id_image;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Tier getTier() {
        return tier;
    }
    public void setTier(Tier tier) {
        this.tier = tier;
    }
}
