package com.example.finalproject_jacksversion;

import java.util.ArrayList;

public class SpiderMite extends Insect {
    private String plantFood;
    private Insect.Move moveType;
    public static ArrayList<SpiderMite> pests = new ArrayList<>();


    public SpiderMite(String species, String color, boolean canFly, String plantType, int row, int col, int numPests) {
        super(row, col, numPests);
        this.plantFood = plantType;
        moveType = Move.Crawl;
    }

    public String getPlantType() {
        return plantFood;
    }

    public void setPlantType() {
        this.plantFood = plantFood;
    }

    public void EatPlant() {
        System.out.println("The spider mite is eating a " + plantFood + " plant.");
        // Eating code goes here
    }


}
