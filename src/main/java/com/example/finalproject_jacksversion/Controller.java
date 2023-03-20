package com.example.finalproject_jacksversion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    @FXML private GridPane gardenGrid;
    @FXML private RadioButton flowerButton;
    @FXML private RadioButton cactusButton;
    @FXML private RadioButton herbButton;
    @FXML private RadioButton vegetableButton;
    @FXML private HBox imageBox = new HBox();
    private static final Image soilImage = new Image("file:Pictures/OIP.jfif");
    public static final Image cactusImage = new Image("file:Pictures/R.png");
    public static final Image flowerImage = new Image("file:Pictures/simple-flower-pink-hi.png");
    public static final Image herbImage = new Image("file:Pictures/clipart-leaves-tulsi-leaf-2.png");
    public static final Image vegetableImage = new Image("file:Pictures/Tomato.png");
    public static final Image beeImage = new Image("file:Pictures/Bee.png");
    public static Set<String> occupiedCells = new HashSet<>();
    public static Set<String> occupiedFlowerCells = new HashSet<>();
    public static Set<String> occupiedBeeCells = new HashSet<>();
    private Timeline timeline;
    @FXML private Label userInfoLabel;
    private int day = 2;


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
                userInfoLabel.setText("Today is Day 1");
            }
        }
        Timer.setStartTime(); //alex check with team tomorrow, delete if wrong
    }


    @FXML
    public void plantPlants() {
        EventHandler<MouseEvent> plantHandler = event -> {
            Node node = (Node) event.getSource();
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
        };
        for (Node node : gardenGrid.getChildren()) {
            node.setOnMouseClicked(plantHandler);
        }
    }


    public void plantFlower(int row, int col) throws FileNotFoundException {
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid);
        flower.plant(row, col);
        Plant.plantsList.add(flower);
    }


    public void plantCactus(int row, int col) throws FileNotFoundException {
        Cactus cactus = new Cactus("Cactus1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid);
        cactus.plant(row, col);
        Plant.plantsList.add(cactus);
    }

    public void plantHerb(int row, int col) throws FileNotFoundException {
        Herb herb = new Herb("sdfdsf", "sfd", 10, "red", 10, "reedsfdsr", row, col, gardenGrid);
        herb.plant(row, col);
        Plant.plantsList.add(herb);
    }

    public void plantVegetable(int row, int col) throws FileNotFoundException {
        Vegetable vegetable = new Vegetable(row, col, gardenGrid);
        vegetable.plant(row, col);
        Plant.plantsList.add(vegetable);
    }

    /*
   @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            addBeesToCells();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

     */



    public void addBeesToCells() {
        for (String cell: occupiedFlowerCells) {
            if (!occupiedBeeCells.contains(cell)) {
                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));;
                ImageView beeView = new ImageView();
                beeView.setFitHeight(25);
                beeView.setFitWidth(25);
                beeView.setImage(beeImage);
                imageBox.getChildren().add(beeView);
                occupiedBeeCells.add(cell);
            }
        }
    }



    public void iterateDay() {
        userInfoLabel.setText("Today is Day " + day);
        day++;
        addBeesToCells();
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