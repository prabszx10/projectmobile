package com.example.projectmobile;

public class Internasional {
    String recipeimage;
    String name;
    String step;
    String id;
    String ingredients;
    String username;

    public Internasional(String recipeimage, String name, String step, String id, String ingredients, String username) {
        this.recipeimage = recipeimage;
        this.name = name;
        this.step = step;
        this.id = id;
        this.ingredients = ingredients;
        this.username = username;
    }

    public Internasional() {
    }

    public String getRecipeimage() {
        return recipeimage;
    }

    public void setRecipeimage(String recipeimage) {
        this.recipeimage = recipeimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
