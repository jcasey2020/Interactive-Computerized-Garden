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
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;



import static com.example.finalproject_jacksversion.Bee.bees;
import static com.example.finalproject_jacksversion.Flower.flowerImageViewMap;
import static com.example.finalproject_jacksversion.Flower.flowers;


public class Controller {
    @FXML
    private GridPane gardenGrid;
    @FXML
    private GridPane weatherGrid;
    @FXML
    private Pane imagePane;
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
    private final Image sunnyGardenImage= new Image("file:Pictures/sunnyGarden.jpg");
    private final Image cloudyGardenImage = new Image("file:Pictures/cloudyGarden.jpg");
    private final Image rainyGardenImage = new Image("file:Pictures/rainyGarden.jpg");
    private final Image spiderMiteImage = new Image("file:Pictures/SpiderMite.png");
    public static Set<String> occupiedCells = new HashSet<>();
    public static Set<String> occupiedFlowerCells = new HashSet<>();
    public static Set<String> occupiedBeeCells = new HashSet<>();

    public static ArrayList<String> occupiedSpidermiteCells = new ArrayList<>();
    private Timeline timeline;

    @FXML
    private Label userInfoLabel;
    @FXML
    private Label label2;
    @FXML
    private Label systemLabel;

    public static int day = 1;
    public static final Map<Bee, ImageView> beeImageViewMap = new HashMap<>();
    private Log log;

    public Controller() throws IOException {
        String logDirectory = "Logs";
        this.log = new Log(day, "Logs");
    }

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
                userInfoLabel.setText("   Today is Day 1");
            }
        }
        Timer.setStartTime();//alex check with team tomorrow, delete if wrong
        chooseWeather();
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
        occupiedCells.add(cell);
        occupiedFlowerCells.add(cell);
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0);
        flowerImageViewMap.put(flower, plantView);
        flowers.add(flower);
        log.info("Flower planted at row " + row + ", column " + col);
    }


    public void plantCactus(int row, int col) throws FileNotFoundException {
        Cactus cactus = new Cactus("Cactus1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid);
        cactus.plant(row, col);
        Plant.plantsList.add(cactus);
        log.info("Cactus planted at row " + row + ", column " + col);
    }

    public void plantHerb(int row, int col) throws FileNotFoundException {
        Herb herb = new Herb("sdfdsf", "sfd", 10, "red", 10, "reedsfdsr", row, col, gardenGrid);
        herb.plant(row, col);
        Plant.plantsList.add(herb);
        log.info("Herb planted at row " + row + ", column " + col);
    }

    public void plantVegetable(int row, int col) throws FileNotFoundException {
        Vegetable vegetable = new Vegetable(row, col, gardenGrid);
        vegetable.plant(row, col);
        Plant.plantsList.add(vegetable);
        log.info("Vegetable planted at row " + row + ", column " + col);
    }

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
                Bee bee = new Bee("Honey Bee", "Yellow", true, "Flower", Insect.Move.Fly, row, col);
                beeImageViewMap.put(bee, beeView);
                bees.add(bee);
                log.info("Bee pollinated flower at row " + row + ", column " + col);
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
            ImageView beeView = beeImageViewMap.get(bee);
            HBox imageBox = (HBox) beeView.getParent();
            imageBox.getChildren().remove(beeView);
            beeImageViewMap.remove(bee);
            beesToRemove.add(bee);
        }
        bees.removeAll(beesToRemove);
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
                    log.info("Flower at row " + row + ", column " + col + " died because it had not been pollinated for two consecutive days.");
                }
            }
        }
        flowers.removeAll(flowersToRemove);
    }

        public void iterateDay () throws IOException {
            day++;
            log = new Log(day, "Logs");
            userInfoLabel.setText("Today is Day " + day);
            waterHeatPlant();
            chooseWeather();
            removeBeesFromCells();
            addBeesToCells(flowers);
            die();
            for (Flower flower : flowers) {
                flower.timeSincePollenated++;
            }
            spawnPests();
            pestControl();
        }

        public void spawnPests () {
            for (String cell : occupiedCells) {
                Random ran = new Random();
                int rando = ran.nextInt(2);

                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                Collections.sort(occupiedSpidermiteCells);
                if (!occupiedFlowerCells.contains(cell) && !occupiedSpidermiteCells.contains(cell) && day % 2 == 0 && rando == 1) {

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

                }
            }
        }

        public void pestControl() {
            List<Insect> insectsToRemove = new ArrayList<>();
            for (Insect s : Insect.insectsList) {
                Random ran = new Random();
                int rando = ran.nextInt(2);
                if (rando == 1) {
                    ImageView spiderView = PestControl.insectViewMap.get(s);

                    if (spiderView != null) {
                        // remove ImageView from grid
                        HBox imageBox = (HBox) spiderView.getParent();
                        imageBox.getChildren().remove(spiderView);

                        insectsToRemove.add(s);
                        PestControl.insectViewMap.remove(s);
                        occupiedSpidermiteCells.remove((s));
                    }
                }
            }
            Insect.insectsList.removeAll(insectsToRemove);
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
                    systemLabel.setText("\n\n\nSprinklers ON \n Heater ON");
                }
                else if (day % 2 == 0) { //sprinkler system
                    rainView.setFitWidth(40);
                    rainView.setImage(waterImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                    systemLabel.setText("\n\n\nSprinklers ON");
                }
                else if(day%3==0){ //heat system
                    rainView.setFitWidth(40);
                    rainView.setImage(sunImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                    systemLabel.setText("\n\n\nHeater ON");
                }
                else{
                    systemLabel.setText(" ");
                    clearTable();
                }
            }
        }
    }
    public void clearTable() {
        weatherGrid.getChildren().clear();
    }

    public void chooseWeather(){
        int randomChoice = (int)(Math.random()*3+1);
        ImageView weatherView = new ImageView();
        weatherView.setFitHeight(435);
        weatherView.setFitWidth(500);
        if(randomChoice==1) {
            weatherView.setImage(sunnyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is sunny in the garden!");
        }
        else if(randomChoice==2){
            weatherView.setImage(rainyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is rainy in the garden");
        }
        else{
            weatherView.setImage(cloudyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is cloudy in the garden");
        }

    }
}

/*

        public void waterHeatPlant () {
            int rows = weatherGrid.getRowCount();
            int cols = weatherGrid.getColumnCount();
            clearTable();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    HBox weatherBox = new HBox();
                    ImageView rainView = new ImageView();
                    rainView.setFitHeight(40);

                    if (day % 2 == 0 && day % 3 == 0) { //both heat and sprinkler
                        rainView.setFitWidth(55);
                        rainView.setImage(sunWaterImage);
                        weatherBox.getChildren().add(rainView);
                        weatherGrid.add(weatherBox, row, col);
                    } else if (day % 2 == 0) { //sprinkler system
                        rainView.setFitWidth(40);
                        rainView.setImage(waterImage);
                        weatherBox.getChildren().add(rainView);
                        weatherGrid.add(weatherBox, row, col);
                    } else if (day % 3 == 0) { //heat system
                        rainView.setFitWidth(40);
                        rainView.setImage(sunImage);
                        weatherBox.getChildren().add(rainView);
                        weatherGrid.add(weatherBox, row, col);
                    } else {
                        clearTable();
                    }
                }
            }
        }

 */

