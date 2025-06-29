//модель тирлиста
package com.valeevVA.tierlistBackend.model.tierlist;

import jakarta.persistence.*;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.model.tier.Tier;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(schema = "public")
public class Tierlist {

    //ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tierlist; //айди тирлиста (ключ)
    private String name; //имя тирлиста
    private Boolean publicity; //публичность (true = публичный, false = скрытый (по ссылке))

    //СВЯЗИ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user; //связь один-ко-многим пользователь --> тирлист

    //КОНСТРУКТОРЫ
    public Tierlist() {}
    public Tierlist(int id_tierlist, String name, Boolean publicity, User user) {
        this.id_tierlist = id_tierlist;
        this.name = name;
        this.publicity = publicity;
        this.user = user;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_tierlist() {
        return id_tierlist;
    }
    public void setId_tierlist(int id_tierlist) {
        this.id_tierlist = id_tierlist;
    }
    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }
    public Boolean getPublicity() {
        return publicity;
    }
    public void setPublicity(Boolean publicity) {
        this.publicity = publicity;
    }
    public User getUser() {
        return user;
    }
    public  void setUser(User user) {
        this.user = user;
    }
}
