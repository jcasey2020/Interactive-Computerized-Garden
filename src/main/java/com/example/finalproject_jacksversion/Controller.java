package com.example.finalproject_jacksversion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static com.example.finalproject_jacksversion.Bee.bees;
//import static com.example.finalproject_jacksversion.Flower.flowerImageViewMap;
import static com.example.finalproject_jacksversion.Flower.flowers;
import static com.example.finalproject_jacksversion.Insect.insectsList;
import static com.example.finalproject_jacksversion.Plant.plantImageViewMap;
import static com.example.finalproject_jacksversion.Plant.plantsList;
import static com.example.finalproject_jacksversion.SpiderMite.pests;

import java.util.Timer;
import java.util.TimerTask;
public class Controller{
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
    private final Image fireImage = new Image("file:Pictures/Large_bonfire.jpg");
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
    public static final Map<SpiderMite, ImageView> pestImageViewMap = new HashMap<>();
    private Log log;
    public Button pressToPlayButton;
    public Button iterateDayButton;

    public Controller() throws IOException {
        String logDirectory = "Logs";
        this.log = new Log(day, "Logs");
    }

    //here we initialize our garden: fill all the cells with soil, set the first weather, and first day
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
        chooseWeather();
        pressToPlayButton.setDisable(true);

    }

    //this is our method to call each plant method depending on which button is selected
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

    //planting a flower; creating flower object and adding it to the gird
    public void plantFlower(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedFlowerCells.contains(cell)) { return; }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(flowerImage);
        imageBox.getChildren().add(plantView);
        occupiedFlowerCells.add(cell);
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0, 0);
        Plant.plantImageViewMap.put(flower, plantView);
        flowers.add(flower);
        log.info("Flower planted at row " + row + ", column " + col);
    }


    //planting a cactus; create catus object and add it to the grid
    public void plantCactus(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(cactusImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Cactus cactus = new Cactus("Cactus1", "Pink Rose", 1, "Pink", 2, 5, row, col, gardenGrid, 0);
        Plant.plantImageViewMap.put(cactus, plantView);
        Plant.plantsList.add(cactus);
        log.info("Cactus planted at row " + row + ", column " + col);
    }

    //planting a herb; create herb object and add it to the grid
    public void plantHerb(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(herbImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Herb herb = new Herb("sdfdsf", "sfd", 10, "red", 10, "reedsfdsr", row, col, gardenGrid, 0);
        Plant.plantImageViewMap.put(herb, plantView);
        Plant.plantsList.add(herb);
        log.info("Herb planted at row " + row + ", column " + col);
    }

    //planting a vegetable; create vegetable object and add it to the grid
    public void plantVegetable(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(vegetableImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Vegetable vegetable = new Vegetable(row, col, gardenGrid, 0);
        Plant.plantImageViewMap.put(vegetable, plantView);
        Plant.plantsList.add(vegetable);
        log.info("Vegetable planted at row " + row + ", column " + col);
    }

    //adding bees to randomly pollinate flowers in the garden
    public void addBeesToCells() {

        for (String cell : occupiedFlowerCells) {
            Random random = new Random();
            int num = random.nextInt(3);
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
                Bee bee = new Bee("Honey Bee", "Yellow", true, "Flower", Insect.Move.Fly, row, col, 0);
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

    //removing bees from grid
    public void removeBeesFromCells() {
        List<Bee> beesToRemove = new ArrayList<>();
        for (Bee bee : bees) {
            int col = bee.getCol();
            int row = bee.getRow();
            ImageView beeView = beeImageViewMap.get(bee);
            HBox imageBox = (HBox) beeView.getParent();
            imageBox.getChildren().remove(beeView);
            beeImageViewMap.remove(bee, beeView);
            beesToRemove.add(bee);
        }
        bees.removeAll(beesToRemove);
    }

    //remove plant object from grid
    public void die() {
        List<Flower> flowersToRemove = new ArrayList<>();
        for (Flower flower : Flower.flowers) {
            if (flower.timeSincePollenated >= 5) {
                int col = flower.getCol();
                int row = flower.getRow();
                String cell = row + "," + col;
                ImageView flowerView = Plant.plantImageViewMap.get(flower);

                if (flowerView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) flowerView.getParent();
                    imageBox.getChildren().remove(flowerView);

                    // remove association between Flower object and ImageView
                    Plant.plantImageViewMap.remove(flower);
                    //occupiedCells.remove(cell);
                    occupiedFlowerCells.remove(cell);
                    flowersToRemove.add(flower);
                    log.info("Flower at row " + row + ", column " + col + " died because it had not been pollinated for two consecutive days.");
                    //Plant.plantsList.remove(flower);
                }
            }
        }
        flowers.removeAll(flowersToRemove);
    }


    //incrementing each day and calling appropriate methods to run each day
        public void iterateDay () throws IOException {

                day++;

                log = new Log(day, "Logs");

            userInfoLabel.setText("Today is Day " + day);
                waterHeatPlant();
                removeBeesFromCells();
                addBeesToCells();
                die();
                //removePestsFromCells();
                pestControl();
                pestKillPlant();
                addPestsToCells();
                chooseWeather();
                for (Flower flower : flowers) {
                    flower.timeSincePollenated++;
                }
                if (!occupiedCells.isEmpty()){
                    //pests();
                }

        }

    public void iterateDayWithTimer() throws InterruptedException, IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<>() {
            int i = 0;
            @Override
            public void handle(ActionEvent event) {
                try {
                    iterateDay();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        }));
        timeline.setCycleCount(100);
        timeline.play();
        iterateDayButton.setDisable(true);
    }

    public void finish(){
        System.exit(0);
    }

    public void addPestsToCells() {
        for (String cell : occupiedCells) {
            Random random = new Random();
            int num = random.nextInt(3);
            if (num == 1 && !occupiedSpidermiteCells.contains(cell)) {
                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
                ImageView pestView = new ImageView();
                pestView.setFitHeight(25);
                pestView.setFitWidth(25);
                pestView.setImage(spiderMiteImage);
                imageBox.getChildren().add(pestView);
                SpiderMite spiderMite = new SpiderMite("Honey Bee", "Yellow", true, "Flower", row, col, 0);
                pestImageViewMap.put(spiderMite, pestView);
                occupiedSpidermiteCells.add(cell);
                pests.add(spiderMite);
                log.info("SpiderMite spawned on plant at row " + row + ", column " + col);
                for (Plant plant: plantsList) {
                    if (plant.getRow() == row && plant.getCol() == col) {
                        plant.numPests++;
                    }
                }
            }
        }
    }

    public void pestKillPlant() {
        List<Plant> plantsToRemove = new ArrayList<>();
        List<SpiderMite> pestsToRemove = new ArrayList<>();
        for (Plant plant : plantsList) {
            if (plant.numPests >= 5) {
                int col = plant.getCol();
                int row = plant.getRow();
                String cell = row + "," + col;
                ImageView plantView = Plant.plantImageViewMap.get(plant);

                if (plantView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) plantView.getParent();
                    imageBox.getChildren().remove(plantView);

                    // remove association between Flower object and ImageView
                    Plant.plantImageViewMap.remove(plant, plantView);
                    occupiedCells.remove(cell);
                    plantsToRemove.add(plant);
                    log.info("Plant at row " + row + ", column " + col + " died because of too many spiderMites.");
                    for (SpiderMite pest : pests) {
                        if (pest.getRow() == row && pest.getCol() == col) {
                            ImageView pestView = pestImageViewMap.get(pest);
                            imageBox.getChildren().remove(pestView);
                            pestImageViewMap.remove(pest, pestView);
                            pestsToRemove.add(pest);
                        }
                    }
                }
            }
        }
        plantsList.removeAll(plantsToRemove);
        pests.removeAll(pestsToRemove);
    }

    public void pestControl() {
        List<Insect> insectsToRemove = new ArrayList<>();

        for (SpiderMite spiderMite : pests) {
            Random ran = new Random();
            int rando = ran.nextInt(8);

            String cell = spiderMite.getRow() + "," + spiderMite.getCol();
            if (rando != 1) {
                ImageView spiderView = pestImageViewMap.get(spiderMite);

                if (spiderView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) spiderView.getParent();
                    imageBox.getChildren().remove(spiderView);
                    log.info("Pest control wiped out SpiderMite at row " + spiderMite.getRow() + ", column " + spiderMite.getCol() + ".");
                    insectsToRemove.add(spiderMite);

                    pestImageViewMap.remove(spiderMite);
                    occupiedSpidermiteCells.remove(cell);
                }
            }
        }
        pests.removeAll(insectsToRemove);

    }

    //sprinkler and heating system for garden; automatically waters every 2 days and heats every 3 days
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
                    systemLabel.setText("Sprinklers ON \n Heater ON");

                }
                else if (day % 2 == 0) { //sprinkler system
                    rainView.setFitWidth(40);
                    rainView.setImage(waterImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                    systemLabel.setText("Sprinklers ON");
                }
                else if(day%3==0){ //heat system
                    rainView.setFitWidth(40);
                    rainView.setImage(sunImage);
                    weatherBox.getChildren().add(rainView);
                    weatherGrid.add(weatherBox, row, col);
                    systemLabel.setText("Heater ON");
                }
                else{
                    systemLabel.setText(" ");
                    clearTable();
                }
            }
        }
        if(day%2==0 && day%3==0) {
            log.info("Sprinkler and heating system ON today");
        }
        else if (day % 2 == 0) {
            log.info("Sprinkler system ON today");
        }
        else if(day%3==0){
            log.info("Heating system ON today");
        }
    }

    //removing heating and watering images
    public void clearTable() {
        weatherGrid.getChildren().clear();
    }

    //randomly choosing and displaying a weather in the garden each day
    public void chooseWeather(){
        int randomChoice = (int)(Math.random()*3+1);
        ImageView weatherView = new ImageView();
        weatherView.setFitHeight(330);
        weatherView.setFitWidth(450);
        if(randomChoice==1) {
            weatherView.setImage(sunnyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is sunny!");
            log.info("It was sunny in the garden today!");
        }
        else if(randomChoice==2){
            weatherView.setImage(rainyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is rainy");
            log.info("It was rainy in the garden today");
        }
        else{
            weatherView.setImage(cloudyGardenImage);
            imagePane.getChildren().add(weatherView);
            label2.setText("Today it is cloudy");
            log.info("It was cloudy in the garden today");
        }

    }

    public void clearGarden() {
        gardenGrid.getChildren().clear();
    }
/*
    public void burn() throws FileNotFoundException, InterruptedException {
        ImageView burnView = new ImageView();
        burnView.setFitHeight(330);
        burnView.setFitWidth(450);
        burnView.setImage(fireImage);
        imagePane.getChildren().add(burnView);
        label2.setText("YOU BURNED THE GARDEN!");
        log.info("You burned down the garden today");

    }

 */

}
