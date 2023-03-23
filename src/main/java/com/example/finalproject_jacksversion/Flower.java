package com.example.finalproject_jacksversion;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public int timeSincePollenated;

    public void setTimeSincePollenated(int timeSincePollenated) { this.timeSincePollenated = timeSincePollenated; }
    public int getTimeSincePollenated() { return timeSincePollenated; }

    public Flower(String name, String species, int size, String color, int growthRate, int numPetals, int row, int col, GridPane gardenGrid, int timeSincePollenated, int numPests){
        super(row, col, numPests);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.numPetals = numPetals;
        this.predator=new SpiderMite("SpiderMite", "Black", false, "Flower", row, col, 0);//or whatever we want flower predators to be
        this.gardenGrid = gardenGrid;
        this.timeSincePollenated = timeSincePollenated;
    }

    public int getNumPetals(){
        return numPetals;
    }

    public void setNumPetals(int numPetals){
        this.numPetals = numPetals;
    }

    public static final Map<Flower, ImageView> flowerImageViewMap = new HashMap<>();


    public void plant(int row, int col) throws FileNotFoundException {
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
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0, 0);
        flowerImageViewMap.put(flower, plantView);
    }


    public void die(Flower flower) {
        //for (Flower flower : Flower.flowers) {
            if (flower.timeSincePollenated >= 2) {
                int col = flower.getCol();
                int row = flower.getRow();
                String cell = row + "," + col;
                ImageView flowerView = flowerImageViewMap.get(flower);

                // remove ImageView from grid
                HBox imageBox = (HBox) flowerView.getParent();
                imageBox.getChildren().remove(flowerView);

                // remove association between Flower object and ImageView
                flowerImageViewMap.remove(flower);
                occupiedCells.remove(cell);
                occupiedFlowerCells.remove(cell);
           // }
        }
    }



    public void removeOldFlowers() {
        List<Flower> oldFlowers = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.timeSincePollenated >= 2) {
                int col = flower.getCol();
                int row = flower.getRow();
                String cell = row + "," + col;
                if (occupiedCells.contains(cell)) {
                    HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
                    for (Node node : new ArrayList<>(imageBox.getChildren())) {
                        if (node instanceof ImageView) {
                            ImageView imageView = (ImageView) node;
                            Object userData = imageView.getUserData();
                            if (userData != null && userData instanceof Flower && userData == flower) {
                                if (imageView.getImage() == flowerImage) {
                                    imageBox.getChildren().remove(node);
                                }
                                occupiedCells.remove(cell);
                                occupiedFlowerCells.remove(cell);
                            }
                        }
                    }
                }
                oldFlowers.add(flower);
            }
        }
        flowers.removeAll(oldFlowers);
    }





}