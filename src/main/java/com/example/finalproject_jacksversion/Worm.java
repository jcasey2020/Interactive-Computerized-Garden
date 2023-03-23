package com.example.finalproject_jacksversion;

public class Worm extends Insect {
    private String plantToFertilize;
    private Insect.Move moveType = Insect.Move.Crawl;

    public Worm(String species, String color, boolean canFly, String plantType, Insect.Move moveType, int row, int col, int numPests) {
        super(row, col, numPests);
        this.plantToFertilize = plantType;
        this.moveType = moveType;
    }

    public String getPlantType() {
        return plantToFertilize;
    }

    public void setPlantType() {
        this.plantToFertilize = plantToFertilize;
    }

    public void EatPlant() {
        System.out.println("The worm is fertilizing the soil for a " + plantToFertilize + " plant.");
        // Eating code goes here
    }
}