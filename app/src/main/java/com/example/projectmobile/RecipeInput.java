package com.example.projectmobile;

import android.widget.EditText;

public class RecipeInput {
    String name;
    String step;
    String id;

    public RecipeInput() {
    }

    public RecipeInput(String name, String step, String id) {
        this.name = name;
        this.step = step;
        this.id = id;
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
}
