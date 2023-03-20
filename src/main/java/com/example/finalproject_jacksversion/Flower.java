package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import static com.example.finalproject_jacksversion.Controller.*;

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
    private GridPane gardenGrid;


    private int numPetals;

    public Flower(String name, String species, int size, String color, int growthRate, int numPetals, int row, int col, GridPane gardenGrid){
        super(row, col);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.numPetals = numPetals;
        this.predator=new SpiderMite("SpiderMite", "Black", false, "Flower", Insect.Move.Crawl, row, col);//or whatever we want flower predators to be
        this.gardenGrid = gardenGrid;
    }

    public int getNumPetals(){
        return numPetals;
    }

    public void setNumPetals(int numPetals){
        this.numPetals = numPetals;
    }

    public void plant(int row, int col) throws FileNotFoundException {
        gardenGrid.getRowCount();
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(flowerImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        occupiedFlowerCells.add(cell);
    }


}
