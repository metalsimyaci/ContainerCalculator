package com.hasanural.containercalculator.DataAccess.Entity;

public class Settings {
    public int id;
    public String key;
    public String value;
    public Settings(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getkey() {
        return key;
    }

    public void setkey(String definition) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
