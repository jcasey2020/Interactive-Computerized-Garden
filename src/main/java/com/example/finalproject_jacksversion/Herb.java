package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.finalproject_jacksversion.Controller.*;

public class Herb extends Plant{

    private String type;
    //eg. species: tree, shrub, vine
    //    type:    willow tree, hibiscus shrub, clingers (type of vine)
    public static ArrayList<Herb> herbs = new ArrayList<>();
    private GridPane gardenGrid;
    public int numPests;

    public Herb(String name, String species, int size, String color, int growthRate, String type, int row, int col, GridPane gardenGrid, int numPests){
        super(row, col, numPests);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.type = type;
        this.gardenGrid = gardenGrid;
        this.numPests = numPests;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void plant(int row, int col) throws FileNotFoundException {
        gardenGrid.getRowCount();
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(herbImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
    }

}
