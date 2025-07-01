//модель пользователя
package com.valeevVA.tierlistBackend.model.user;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.model.rating.Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
//юзернейм и почта - уникальные!
@Entity
@Table(schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
})
public class User {

    //ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user; //айди пользователя (ключ)

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 20, message = "Имя пользователя должно быть длиной между 3 и 20 символами")
    private String username; //юзернейм (уникальный)
    @NotBlank(message = "Почта не должна быть пустой")
    @Email(message = "Почта должна быть правильного формата (например, example@gmail.com)")
    private String email; //емаил (нужно проверку на формат *@*.*)
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен сожержать хотя бы 6 символов")
    private String password_hash; //пароль (хеширование)

    //СВЯЗИ
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tierlist> tierlists = new ArrayList<>(); //связь один-ко-многим пользователь --> тирлист
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>(); //связь один-ко-многим пользователь --> оценка

    //КОНСТРУКТОРЫ
    public User() {}
    public User(int id_user, String username, String email, String password_hash) {
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }

    //ГЕТТЕРЫ И СЕТТЕРЫ
    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword_hash() {
        return password_hash;
    }
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public List<Tierlist> getTierlists() {
        return tierlists;
    }
    public void setTierlists(List<Tierlist> tierlists) {
        this.tierlists = tierlists;
    }
    public List<Rating> getRatings() {
        return ratings;
    }
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
