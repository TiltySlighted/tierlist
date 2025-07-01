package com.valeevVA.tierlistBackend.dto;

public class TierlistDTO {
    private int id;
    private String name;
    private boolean publicity;

    // Конструкторы, геттеры и сеттеры
    public TierlistDTO(int id, String name, boolean publicity) {
        this.id = id;
        this.name = name;
        this.publicity = publicity;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isPublicity() {
        return publicity;
    }
}
