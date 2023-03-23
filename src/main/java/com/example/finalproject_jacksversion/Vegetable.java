package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

import static com.example.finalproject_jacksversion.Controller.*;

public class Vegetable extends Plant{
    int water;
    int harvestTime;
    int timesHarvested;
    int wateringFreq;
    private GridPane gardenGrid;
    public int numPests;

    private int produceVeggie;
    public Vegetable(int row, int col, GridPane gardenGrid, int numPests){
        super(row, col, numPests);
        setWateringFreq(1);
        this.harvestTime=3;
        this.timesHarvested=0;
        this.predator=new Snail("Snail","green",false, "vegtable", Insect.Move.Crawl, row, col, 0);//adjust to predator
        produceVeggie=0;
        this.gardenGrid = gardenGrid;
        this.numPests = numPests;
    }
    public int getHarvestTime(){
        return this.harvestTime;
    }
    public int getProduceVeggie(){
        return produceVeggie;
    }
    @Override
    public void dayPassed() {
        super.dayPassed();
        produceVeggie++;
    }

    public void setHarvestTime(int harvestTime){
        this.harvestTime=harvestTime;
    }
    public void harvest(){ //harvest veggies every 5 days
        if(produceVeggie%5==0 && isAlive()) {
            timesHarvested++;
        }
    }

    public void plant(int row, int col) throws FileNotFoundException {
        gardenGrid.getRowCount();
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(vegetableImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
    }


}
    /*
    private String type;
    //eg. species would be "Potato", type would be "Yukon"

    private boolean edible, inGround;
    //eg. are the veggies edible yet? yes or no
    //eg. are the veggies grown in soil or no

    /*public Vegetable(String name, String species, int size, String color, int growthRate, String type, boolean edible, boolean inGround){
        super.setName(name);
        super.setSpecies(species);
        super.setSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.type = type;
        this.edible = edible;
        this.inGround = inGround;
    }


    public String getType(){
        return this.type;
    }

    public boolean isEdible() {
        return edible;
    }

    public boolean isInGround() {
        return inGround;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public void setInGround(boolean inGround){
        this.inGround = inGround;
    }

     */

