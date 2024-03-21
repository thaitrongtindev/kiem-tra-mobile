package com.example.kiemtra;


import java.io.Serializable;


public class ObjectModel implements Serializable {
    public int quantities;
    public String name;

    public ObjectModel(int quantities, String name) {
        this.quantities = quantities;
        this.name = name;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



