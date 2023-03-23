package com.example.finalproject_jacksversion;

import java.util.ArrayList;

public class Insect extends Plant {
    // Bees, Spider Mites, Snails, Worms, Slugs
    private String species;
    private String color;
    private boolean canFly;
    private Plant food; // assuming a Plant enumeration has been defined in the Plant class
    private Move moveType;
    public static ArrayList<Insect> insectsList = new ArrayList<Insect>();

    public enum Move { Fly, Crawl, Climb }

    //public Insect(String species, String color, boolean canFly) {}

    public Insect(int row, int col, int numPests) {
        super(row, col, numPests);
        this.species = species;
        this.color = color;
        this.canFly = canFly;
        this.food = food;

    }
    public String getSpecies() { return species; }
    public void setSpecies(String species){
        this.species = species;
    }
    public String getColor() { return color; }
    public void setColor(String color){ this.color = color; }
    public boolean isCanFly() { return canFly; }
    public void setCanFly(boolean canFly) { this.canFly = canFly; }
    public Plant getPlant() { return food; }
    public void setPlant() { this.food = food; }

}
