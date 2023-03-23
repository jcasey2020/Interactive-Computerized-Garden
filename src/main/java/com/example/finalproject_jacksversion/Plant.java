package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plant {
    private String name;
    private String species;
    private String color;
    private int growthRate;
    private int wateringFreq;
    private boolean alive;
    private int age;
    private int plantSize; //keeps track of how big plant is/number of grow cycles
    Insect predator;
    public static ArrayList<Plant> plantsList = new ArrayList<Plant>();
    public static final HashMap<Plant, ImageView> plantImageViewMap = new HashMap<>();
    private int row;
    private int col;

    public int numPests;



    public Plant(int row, int col, int numPests){
        this.wateringFreq=2;
        this.alive=true;
        this.age=0;
        this.plantSize=0;
        this.row = row;
        this.col = col;
        this.numPests = numPests;
    }

    

    public int getWateringFreq(){
        return wateringFreq;
    }
    public void setWateringFreq(int wateringFreq){
        this.wateringFreq=wateringFreq;
    }
    public boolean isAlive(){
        return this.alive;
    }

    public int getAge(){
        return this.age;
    }

    public int getPlantSize(){
        return this.plantSize;
    }

    public void dayPassed(){
        age++;
    }

    public void passAway(){
        alive=false;
    }

    public void grow(){ //set to grow every three days
        if(getAge()%3==0 && isAlive()) {
            plantSize++;
        }
    }

    public void setName(String name) { this.name = name; }
    public void setPlantSize(int plantSize) { this.plantSize = plantSize; }
    public void setSpecies(String species) { this.species = species; }
    public void setColor(String color) { this.color = color; }
    public void setGrowthRate(int growthRate) { this.growthRate = growthRate; }



    public int getRow() { return this.row; }
    public int getCol() {return this.col; }


}


   /* public Plant(String name, String species, String color, int size, int growthRate){
        this.name = name;
        this.species = species;
        this.color = color;
        this.size = size;
        this.growthRate = growthRate;
    }



    public String getName(){
        return this.name;
    }

    public String getSpecies(){
        return this.species;
    }

    public String getColor() {
        return color;
    }



    public int getSize(){
        return this.size;
    }

    public int getGrowthRate() {
        return growthRate;
    }

    public void setName(String name){
       this.name = name;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    */

