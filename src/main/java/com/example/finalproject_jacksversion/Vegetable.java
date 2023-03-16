package com.example.finalproject_jacksversion;

public class Vegetable extends Plant{
    int water;
    int harvestTime;
    int timesHarvested;
    int wateringFreq;

    private int produceVeggie;
    public Vegetable(int row, int col){
        super(row, col);
        setWateringFreq(1);
        this.harvestTime=3;
        this.timesHarvested=0;
        this.predator=new Snail("Snail","green",false, "vegtable", Insect.Move.Crawl, row, col);//adjust to predator
        produceVeggie=0;
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

