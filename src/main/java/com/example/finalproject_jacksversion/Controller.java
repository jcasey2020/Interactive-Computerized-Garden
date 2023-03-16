package com.example.finalproject_jacksversion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;

public class Controller {
    @FXML private GridPane gardenGrid;
    @FXML private RadioButton flowerButton;
    @FXML private RadioButton cactusButton;
    @FXML private RadioButton herbButton;
    @FXML private RadioButton vegetableButton;
    private Image soilImage;
    private Image cactusImage;
    private Image flowerImage;
    private Image herbImage;
    private Image vegetableImage;

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
                ImageView imageView = new ImageView();
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                imageView.setImage(soilImage);
                gardenGrid.add(imageView, row, col);
            }
        }
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

    public void plantFlower(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(flowerImage);
        gardenGrid.add(imageView, col, row);
        Flower flower = new Flower("Flower1", "Pink Rose", 1, "Pink", 2, 5, row, col);
        Plant.plantsList.add(flower);

    }

    public void plantCactus(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(cactusImage);
        gardenGrid.add(imageView, col, row);
    }

    public void plantHerb(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(herbImage);
        gardenGrid.add(imageView, col, row);
    }

    public void plantVegetable(int row, int col) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setImage(vegetableImage);
        gardenGrid.add(imageView, col, row);
    }

}