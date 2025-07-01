package com.valeevVA.tierlistBackend.dto;

public class SimplifiedTierDTO {
    private int id;
    private String color;
    private String tiername;
    private int pos;

    // Конструктор, геттеры, сеттеры
    public SimplifiedTierDTO(int id, String color, String tiername, int pos) {
        this.id = id;
        this.color = color;
        this.tiername = tiername;
        this.pos = pos;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
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
}
