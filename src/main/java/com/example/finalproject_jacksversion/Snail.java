package com.example.finalproject_jacksversion;

public class Snail extends Insect {
    private String plantFood;
    private Insect.Move moveType = Insect.Move.Crawl;

    public Snail(String species, String color, boolean canFly, String plantType, Insect.Move moveType, int row, int col, int numPests) {
        super(row, col, numPests);
        this.plantFood = plantType;
        this.moveType = moveType;
    }

    public String getPlantType() {
        return plantFood;
    }

    public void setPlantType() {
        this.plantFood = plantFood;
    }

    public void EatPlant() {
        System.out.println("The snail is eating a " + plantFood + " plant.");
        // Eating code goes here
    }
}

