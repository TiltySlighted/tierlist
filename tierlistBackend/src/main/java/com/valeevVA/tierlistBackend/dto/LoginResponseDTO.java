package com.valeevVA.tierlistBackend.dto;

public class LoginResponseDTO {
    private int id;
    private String username;
    private String email;
    public LoginResponseDTO() {}
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
