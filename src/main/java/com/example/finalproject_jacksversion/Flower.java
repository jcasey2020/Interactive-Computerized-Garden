package com.example.finalproject_jacksversion;

import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Flower extends Plant {
    public static ArrayList<Flower> flowers = new ArrayList<>();
    /* public Flower(){
         super();
         this.predator=new SpiderMite("spidermite","black",true, "Flower", Insect.Move.Fly);//or whatever we want flower predators to be
     }
     */
    public Insect getPredators(){
        return this.predator;
    }


    private int numPetals;

    public Flower(String name, String species, int size, String color, int growthRate, int numPetals, int row, int col){
        super(row, col);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.numPetals = numPetals;
        this.predator=new SpiderMite("SpiderMite", "Black", false, "Flower", Insect.Move.Crawl, row, col);//or whatever we want flower predators to be
    }

    public int getNumPetals(){
        return numPetals;
    }

    public void setNumPetals(int numPetals){
        this.numPetals = numPetals;
    }
}
