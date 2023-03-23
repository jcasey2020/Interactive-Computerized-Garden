package com.example.finalproject_jacksversion;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.finalproject_jacksversion.Controller.*;

public class Cactus extends Plant{
    private int numArms;
    public static ArrayList<Cactus> cacti = new ArrayList<>();
    private GridPane gardenGrid;
    public int numPests;

    public Cactus(String name, String species, int size, String color, int growthRate, int numArms, int row, int col, GridPane gardenGrid, int numPests){
        super(row, col, numPests);
        super.setName(name);
        super.setSpecies(species);
        super.setPlantSize(size);
        super.setColor(color);
        super.setGrowthRate(growthRate);
        this.numArms = numArms;
        this.gardenGrid = gardenGrid;
        this.numPests = numPests;
    }

    public int getNumArms() {
        return numArms;
    }

    public void setNumArms(int numArms) {
        this.numArms = numArms;
    }

    public void plant(int row, int col) throws FileNotFoundException {
        gardenGrid.getRowCount();
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(cactusImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
    }





}
