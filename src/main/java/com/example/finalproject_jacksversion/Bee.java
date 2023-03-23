package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import static com.example.finalproject_jacksversion.Controller.*;


public class Bee extends Insect {
    private String plantType;
    private Insect.Move moveType = Insect.Move.Fly;
    private GridPane gardenGrid;
    public static ArrayList<Bee> bees = new ArrayList<>();

    public Bee(String species, String color, boolean canFly, String plantType, Insect.Move moveType, int row, int col, int numPests) {
        super(row, col, numPests);
        this.plantType = plantType;
        this.moveType = moveType;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType() {
        this.plantType = plantType;
    }

    public void pollinateFlower() {
        System.out.println("The bee is pollinating a " + plantType + " flower.");
        // Pollination code goes here
    }



}