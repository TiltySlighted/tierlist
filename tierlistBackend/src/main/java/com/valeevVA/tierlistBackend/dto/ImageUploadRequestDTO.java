package com.valeevVA.tierlistBackend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadRequestDTO {
    private MultipartFile file;
    private int tierlistId;

    // Геттеры и сеттеры
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getTierlistId() {
        return tierlistId;
    }

    public void setTierlistId(int tierlistId) {
        this.tierlistId = tierlistId;
    }
}
