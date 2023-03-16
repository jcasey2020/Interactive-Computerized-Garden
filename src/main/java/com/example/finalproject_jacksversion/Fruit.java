package com.example.finalproject_jacksversion;

import java.util.ArrayList;

public class Fruit extends Plant{
    private String type;
    //eg. species would be "Apple", type would be "Granny Smith"

    private boolean edible;
    //eg. are the fruits edible yet? yes or no
    public static ArrayList<Fruit> fruits = new ArrayList<>();

    public Fruit(String name, String species, int size, String color, int growthRate, String type, boolean edible, int row, int col){
        super(row, col);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.type = type;
        this.edible = edible;
    }
    public String getType(){
        return this.type;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }
}
