package com.example.finalproject_jacksversion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    @FXML public static GridPane gardenGrid;
    @FXML private RadioButton flowerButton;
    @FXML private RadioButton cactusButton;
    @FXML private RadioButton herbButton;
    @FXML private RadioButton vegetableButton;
    @FXML private HBox imageBox = new HBox();
    private final Image soilImage;
    private final Image cactusImage;
    private final Image flowerImage;
    private final Image herbImage;
    private final Image vegetableImage;
    private Set<String> occupiedCells = new HashSet<>();

    public Controller() throws FileNotFoundException {
        soilImage = new Image("file:Pictures/OIP.jfif");
        flowerImage = new Image("file:Pictures/simple-flower-pink-hi.png");
        cactusImage = new Image("file:Pictures/R.png");
        herbImage = new Image("file:Pictures/clipart-leaves-tulsi-leaf-2.png");
        vegetableImage = new Image("file:Pictures/Tomato.png");
    }



    @FXML
    public void initializeGarden() throws FileNotFoundException {
        int rows = gardenGrid.getRowCount();
        int cols = gardenGrid.getColumnCount();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                HBox imageBox = new HBox();
                ImageView soilView = new ImageView();
                soilView.setFitHeight(25);
                soilView.setFitWidth(25);
                soilView.setImage(soilImage);
                imageBox.getChildren().add(soilView);
                gardenGrid.add(imageBox, row, col);
            }
        }
        Timer.setStartTime(); //alex check with team tomorrow, delete if wrong
    }


    @FXML
    public void plantPlants() {
        for (Node node : gardenGrid.getChildren()) {
            node.setOnMouseClicked(event -> {
                int row = GridPane.getRowIndex(node);
                int col = GridPane.getColumnIndex(node);
                try {
                        if (flowerButton.isSelected()) {
                            plantFlower(row, col);
                        }
                        if (cactusButton.isSelected()) {
                            plantCactus(row, col);
                        }
                        if (herbButton.isSelected()) {
                            plantHerb(row, col);
                        }
                        if (vegetableButton.isSelected()) {
                            plantVegetable(row, col);
                        }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    @FXML
    public void pestControl(){
        PestControl.killSpiderMite();
    }

    public void plantFlower(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(flowerImage);
        imageBox.getChildren().add(plantView);
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col);
        Plant.plantsList.add(flower);
        occupiedCells.add(cell);
    }




    public void plantCactus(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(cactusImage);
        gardenGrid.add(imageView, col, row);
        Cactus cactus = new Cactus("Cactus1", "Pink Rose", 1, "Pink", 2, 5, row, col);
        Plant.plantsList.add(cactus);
    }

    public void plantHerb(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(herbImage);
        gardenGrid.add(imageView, col, row);
        Herb herb = new Herb("Flower1", "Pink Rose", 1, "Pink", 2, "Rosemary", row, col);
        Plant.plantsList.add(herb);
    }

    public void plantVegetable(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(vegetableImage);
        gardenGrid.add(imageView, col, row);
        Vegetable vegetable = new Vegetable(row, col);
        Plant.plantsList.add(vegetable);
    }
    
    //CHECK WITH JACKK!!!!
    public void removeItem(int row, int col) throws FileNotFoundException{
        HBox imageBox = new HBox();
        ImageView soilView = new ImageView();
        soilView.setFitHeight(25);
        soilView.setFitWidth(25);
        soilView.setImage(soilImage);
        imageBox.getChildren().add(soilView);
        gardenGrid.add(imageBox, row, col);
    }

}