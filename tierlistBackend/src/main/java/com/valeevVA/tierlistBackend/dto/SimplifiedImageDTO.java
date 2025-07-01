package com.valeevVA.tierlistBackend.dto;

public class SimplifiedImageDTO {
    private int id;
    private String base64;

    // Конструктор, геттеры, сеттеры
    public SimplifiedImageDTO(int id, String base64) {
        this.id = id;
        this.base64 = base64;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
