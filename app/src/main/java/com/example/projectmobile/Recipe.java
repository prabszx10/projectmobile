package com.example.projectmobile;

public class Recipe {
    private String name;
    private String asal;

    public Recipe() {
    }

    public Recipe(String name, String asal) {
        this.name = name;
        this.asal = asal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }
}
