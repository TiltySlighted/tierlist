//модель тира (tier)
package com.valeevVA.tierlistBackend.model.tier;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.model.rating.Rating;
import com.valeevVA.tierlistBackend.model.average.Average;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(schema = "public")
public class Tier {

    //ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tier; //айди тира
    private String color; //цвет (hex)
    private String tiername; //название
    private int pos; //позиция (0 = UNRATED, при создании тирлиста должен всегда создаваться)

    //СВЯЗИ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tierlist", referencedColumnName = "id_tierlist")
    private Tierlist tierlist; //связь один-ко-многим тирлист --> тир (tier) без нулей (при создании нового тирлиста добавляется один тир UNRATED
    @OneToMany(mappedBy = "tier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>(); //связь один-ко-многим картинка --> оценка
    @OneToMany(mappedBy = "tier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Average> averages = new ArrayList<>(); //связь один-ко-многим картинка --> средняя_оценка

    //КОНСТРУКТОРЫ
    public Tier() {}
    public Tier(int id_tier, String color, String tiername, int pos, Tierlist tierlist) {
        this.id_tier = id_tier;
        this.color = color;
        this.tiername = tiername;
        this.pos = pos;
        this.tierlist = tierlist;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_tier() {
        return id_tier;
    }
    public void setId_tier(int id_tier) {
        this.id_tier = id_tier;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getTiername() {
        return tiername;
    }
    public void setTiername(String tiername) {
        this.tiername = tiername;
    }
    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
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
    public List<Average> getAverages() {
        return averages;
    }
    public void setAverages(List<Average> averages) {
        this.averages = averages;
    }
}
