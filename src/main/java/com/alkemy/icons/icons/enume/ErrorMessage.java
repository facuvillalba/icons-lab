package com.alkemy.icons.icons.enume;

public enum ErrorMessage {
    COUNTRY_NOT_FOUND("Id country not found"),
    COUNTRY_NOT_UPDATED("Country not updated, id not found or request error"),
    COUNTRY_NOT_DELETED("Country not deleted, id not found."),
    ICON_NOT_FOUND("Id icon not found"),
    ICON_NOT_UPDATED("Icon not updated, id not found or request error"),
    ICON_NOT_DELETED("Icon not deleted, id not found.");

    private String key;

    ErrorMessage(String key){
        this.key = key;
    }

    public String key(){
        return key;
    }
}
