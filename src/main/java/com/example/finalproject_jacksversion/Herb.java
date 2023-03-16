package com.example.finalproject_jacksversion;

import java.util.ArrayList;

public class Herb extends Plant{

    private String type;
    //eg. species: tree, shrub, vine
    //    type:    willow tree, hibiscus shrub, clingers (type of vine)
    public static ArrayList<Herb> herbs = new ArrayList<>();

    public Herb(String name, String species, int size, String color, int growthRate, String type, int row, int col){
        super(row, col);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
}
