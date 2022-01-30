package com.example.twiniot;

public class MesageModel {
    String id,message,imageUrl;
    Long timestamp;

    public MesageModel(String id, String message, Long timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }



    public MesageModel() {
    }
    public MesageModel(String id, String message) {
        this.id = id;
        this.message = message;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
