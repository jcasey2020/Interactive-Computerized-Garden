package com.example.finalproject_jacksversion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

public class Controller {
    @FXML private GridPane gardenGrid;
    @FXML private RadioButton flowerButton;
    @FXML private RadioButton cactusButton;
    @FXML private RadioButton herbButton;
    @FXML private RadioButton vegetableButton;
    @FXML private HBox imageBox = new HBox();
    private static final Image soilImage = new Image("file:Pictures/OIP.jfif");
    private static final Image cactusImage = new Image("file:Pictures/R.png");
    private static final Image flowerImage = new Image("file:Pictures/simple-flower-pink-hi.png");
    private static final Image herbImage = new Image("file:Pictures/clipart-leaves-tulsi-leaf-2.png");
    private static final Image vegetableImage = new Image("file:Pictures/Tomato.png");
    private static final Image beeImage = new Image("file:Pictures/Bee.png");
    public static Set<String> occupiedCells = new HashSet<>();
    public static Set<String> occupiedFlowerCells = new HashSet<>();



    @FXML
    public void initializeGarden() throws FileNotFoundException {
        int rows = gardenGrid.getRowCount() + 1;
        int cols = gardenGrid.getColumnCount() + 1;
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
//    @FXML
//    public void pestControl(){
//        PestControl.killSpiderMite();
//    }

    public void plantFlower(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(flowerImage);
        imageBox.getChildren().add(plantView);
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col);
        Plant.plantsList.add(flower);
        occupiedCells.add(cell);
        occupiedFlowerCells.add(cell);
    }


    public void plantCactus(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(cactusImage);
        imageBox.getChildren().add(plantView);
        Cactus cactus = new Cactus("Cactus1", "Pink Rose", 1, "Pink", 2, 5, row, col);
        Plant.plantsList.add(cactus);
        occupiedCells.add(cell);
    }

    public void plantHerb(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(herbImage);
        imageBox.getChildren().add(plantView);
        Herb herb = new Herb("Flower1", "Pink Rose", 1, "Pink", 2, "Rosemary", row, col);
        Plant.plantsList.add(herb);
        occupiedCells.add(cell);
    }

    public void plantVegetable(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(vegetableImage);
        imageBox.getChildren().add(plantView);
        Herb herb = new Herb("Flower1", "Pink Rose", 1, "Pink", 2, "Rosemary", row, col);
        Plant.plantsList.add(herb);
        occupiedCells.add(cell);
    }



    private Timeline timeline;

    @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            addBeesToCells();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Set<String> occupiedBeeCells = new HashSet<>();

    public void addBeesToCells() {
        for (Node node : gardenGrid.getChildren()) {
            Integer row = GridPane.getRowIndex(node);
            Integer col = GridPane.getColumnIndex(node);
            //if (row != null && col != null) {
                String cell = row + "," + col;
                if (occupiedFlowerCells.contains(cell) && !occupiedBeeCells.contains(cell)) {
                    HBox imageBox = (HBox) node;
                    ImageView beeView = new ImageView();
                    beeView.setFitHeight(25);
                    beeView.setFitWidth(25);
                    beeView.setImage(beeImage);
                    imageBox.getChildren().add(beeView);
                    occupiedBeeCells.add(cell);
                }
            //}
        }
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

    public void pestControl(){
        PestControl.killSpiderMite();
    }

}