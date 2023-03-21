package com.example.finalproject_jacksversion;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.example.finalproject_jacksversion.Bee.bees;
import static com.example.finalproject_jacksversion.Flower.flowerImageViewMap;
import static com.example.finalproject_jacksversion.Flower.flowers;


public class Controller {
    @FXML
    private GridPane gardenGrid;
    @FXML
    private GridPane weatherGrid;
    @FXML
    private RadioButton flowerButton;
    @FXML
    private RadioButton cactusButton;
    @FXML
    private RadioButton herbButton;
    @FXML
    private RadioButton vegetableButton;
    @FXML
    private HBox imageBox = new HBox();
    @FXML
    private HBox weatherBox = new HBox();
    private static final Image soilImage = new Image("file:Pictures/OIP.jfif");
    public static final Image cactusImage = new Image("file:Pictures/R.png");
    public static final Image flowerImage = new Image("file:Pictures/simple-flower-pink-hi.png");
    public static final Image herbImage = new Image("file:Pictures/clipart-leaves-tulsi-leaf-2.png");
    public static final Image vegetableImage = new Image("file:Pictures/Tomato.png");
    public static final Image beeImage = new Image("file:Pictures/Bee.png");
    private final Image sunImage = new Image("file:Pictures/sunshine.png");
    private final Image waterImage = new Image("file:Pictures/water.jpg");
    private final Image sunWaterImage = new Image("file:Pictures/heatWater.png");
    private final Image spiderMiteImage = new Image("file:Pictures/SpiderMite.png");
    public static Set<String> occupiedCells = new HashSet<>();
    public static Set<String> occupiedFlowerCells = new HashSet<>();
    public static Set<String> occupiedBeeCells = new HashSet<>();
    public static ArrayList<String> occupiedSpidermiteCells = new ArrayList<>();
    private Timeline timeline;
    @FXML
    private Label userInfoLabel;
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
        Timer.setStartTime();
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

/*
    public void plantFlower(int row, int col) throws FileNotFoundException {
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0);
        flower.plant(row, col);
        Plant.plantsList.add(flower);
        flowers.add(flower);
    }

 */


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


    public static final Map<Bee, ImageView> beeImageViewMap = new HashMap<>();

    public void addBeesToCells(List<Flower> flowers) {
        for (String cell : occupiedFlowerCells) {
            Random random = new Random();
            int num = random.nextInt(2);
            if (num == 1) {
                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
                ImageView beeView = new ImageView();
                beeView.setFitHeight(25);
                beeView.setFitWidth(25);
                beeView.setImage(beeImage);
                imageBox.getChildren().add(beeView);
                //occupiedBeeCells.add(cell);
                Bee bee = new Bee("Honey Bee", "Yellow", true, "Flower", Insect.Move.Fly, row, col);
                beeImageViewMap.put(bee, beeView);
                bees.add(bee);
                for (Flower flower : flowers) {
                    if (flower.getRow() == row && flower.getCol() == col) {
                        flower.setTimeSincePollenated(0);
                    }
                }
            }
        }
    }

    public void removeBeesFromCells() {
        List<Bee> beesToRemove = new ArrayList<>();

        for (Bee bee : bees) {
            int col = bee.getCol();
            int row = bee.getRow();
            String cell = row + "," + col;
            ImageView beeView = beeImageViewMap.get(bee);

            // remove ImageView from grid
            HBox imageBox = (HBox) beeView.getParent();
            imageBox.getChildren().remove(beeView);

            // remove association between Flower object and ImageView
            beeImageViewMap.remove(bee);
            //occupiedBeeCells.remove(cell);
            beesToRemove.add(bee);
        }

        // remove all bees to be removed from the original collection
        bees.removeAll(beesToRemove);
    }


public void plantFlower(int row, int col) throws FileNotFoundException {
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
    Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0);
    flowerImageViewMap.put(flower, plantView);
    flowers.add(flower);
}

    public void die() {
        List<Flower> flowersToRemove = new ArrayList<>();
        for (Flower flower : Flower.flowers) {
            if (flower.timeSincePollenated >= 3) {
                int col = flower.getCol();
                int row = flower.getRow();
                String cell = row + "," + col;
                ImageView flowerView = flowerImageViewMap.get(flower);

                if (flowerView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) flowerView.getParent();
                    imageBox.getChildren().remove(flowerView);

                    // remove association between Flower object and ImageView
                    flowerImageViewMap.remove(flower);
                    occupiedCells.remove(cell);
                    occupiedFlowerCells.remove(cell);
                    flowersToRemove.add(flower);
                }
            }
        }
        flowers.removeAll(flowersToRemove);
    }

    public void iterateDay() throws FileNotFoundException {
        userInfoLabel.setText("Today is Day " + day);
        day++;
        waterHeatPlant();
        removeBeesFromCells();
        addBeesToCells(flowers);
        die();
        for (Flower flower: flowers) {
            System.out.println(flower.timeSincePollenated);
            flower.timeSincePollenated++;
        }
        spawnPests();
    }

    public void spawnPests()  {
        for (String cell: occupiedCells) {
            Random ran = new Random();
            int rando = ran.nextInt(2);

            String[] parts = cell.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            Collections.sort(occupiedSpidermiteCells);
            if (!occupiedSpidermiteCells.contains(cell) && day % 2 == 0 && rando == 1) {

                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));

                ImageView spiderMite = new ImageView();
                spiderMite.setFitHeight(25);
                spiderMite.setFitWidth(25);
                spiderMite.setImage(spiderMiteImage);
                imageBox.getChildren().add(spiderMite);
                occupiedSpidermiteCells.add(cell);
                SpiderMite spidy = new SpiderMite("ABC", "Brown", false, "Vegetable", row, col);
                Insect.insectsList.add(spidy);
                PestControl.insectViewMap.put(spidy, spiderMite);

            }else if(occupiedSpidermiteCells.contains(cell) && rando ==1){

            }

        }
    }

    public void pestControl() {
        for (Insect s: PestControl.insectViewMap.keySet()){
            Random ran = new Random();
            int rando = ran.nextInt(2);


            if (rando ==1){
                ImageView spiderView = PestControl.insectViewMap.get(s);

                if (spiderView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) spiderView.getParent();
                    imageBox.getChildren().remove(spiderView);

                    PestControl.insectViewMap.remove(s);
                    Insect.insectsList.remove(s);
                    occupiedSpidermiteCells.remove((s));
                }

            }
        }
    }

    public void waterHeatPlant() {
        int rows = weatherGrid.getRowCount();
        int cols = weatherGrid.getColumnCount();
        clearTable();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                HBox weatherBox = new HBox();
                ImageView rainView = new ImageView();
                rainView.setFitHeight(40);

                if(day%2==0 && day%3==0){ //both heat and sprinkler
                    rainView.setFitWidth(55);
                    rainView.setImage(sunWaterImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                }
                else if (day % 2 == 0) { //sprinkler system
                    rainView.setFitWidth(40);
                    rainView.setImage(waterImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                }
                else if(day%3==0){ //heat system
                    rainView.setFitWidth(40);
                    rainView.setImage(sunImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                }
                else{
                    clearTable();
                }
            }
        }
    }

    public void clearTable() {
        weatherGrid.getChildren().clear();
    }
}

