package com.example.finalproject_jacksversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Garden {
    public int rows = 20;
    public int cols = 20;
    public String emptyImage = " ";
    public String[][] garden;
    public static int flowerCount;
    public static int flowerRow;

    public Garden() {
        garden = new String[rows][cols];
    }
    public void populateGarden() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                garden[row][col] = emptyImage; // set all cells to " " initially
            }
        }
    }

    public void printGarden() {
        System.out.println("Here is your garden!");
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(garden[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void AddFlowers() {
        System.out.print("Enter the number of flowers you would like to plant: ");
        Scanner flowerScanner = new Scanner(System.in);
        int flowerCount = flowerScanner.nextInt();
        System.out.print("Enter the row in which you would like to plant the flowers: ");
        int flowerRow = flowerScanner.nextInt();
        for (int i = 0; i < flowerCount; i++) {
            if(garden[flowerRow][i] == emptyImage) {
                //Flower flower = new Flower(Integer.toString(flowerRow) + Integer.toString(i), "Flower", 0, "Red", 2, new Random().nextInt(10));
                //Flower.flowers.add(flower);
                garden[flowerRow][i] = "Fl";
            }
        }
    }

    public void AddFruit() {
        System.out.print("Enter the number of fruits you would like to plant: ");
        Scanner fruitScanner = new Scanner(System.in);
        int fruitCount = fruitScanner.nextInt();
        System.out.print("Enter the row in which you would like to plant the fruits: ");
        int fruitRow = fruitScanner.nextInt();
        for (int i = 0; i < fruitCount; i++) {
            if(garden[fruitRow][i] == emptyImage) {
                //Fruit fruit = new Fruit(Integer.toString(fruitRow) + Integer.toString(i), "Fruit", 0, "Red", 2,"Raspberry", true);
                //Fruit.fruits.add(fruit);
                garden[fruitRow][i] = "Fr";
            }
        }
    }

    public void AddCactus() {
        System.out.print("Enter the number of cactus you would like to plant: ");
        Scanner cactusScanner = new Scanner(System.in);
        int cactusCount = cactusScanner.nextInt();
        System.out.print("Enter the row in which you would like to plant the cactus: ");
        int cactusRow = cactusScanner.nextInt();
        for (int i = 0; i < cactusCount; i++) {
            if(garden[cactusRow][i] == emptyImage) {
                //Cactus cactus = new Cactus(Integer.toString(cactusRow) + Integer.toString(i), "Cactus", 0, "Green", 1, new Random().nextInt(5));
                //Cactus.cacti.add(cactus);
                garden[cactusRow][i] = "Ca";
            }
        }
    }

    public void AddHerb() {
        System.out.print("Enter the number of herbs you would like to plant: ");
        Scanner herbScanner = new Scanner(System.in);
        int herbCount = herbScanner.nextInt();
        System.out.print("Enter the row in which you would like to plant the herbs: ");
        int herbRow = herbScanner.nextInt();
        for (int i = 0; i < herbCount; i++) {
            if(garden[herbRow][i] == emptyImage) {
                //Herb herb = new Herb(Integer.toString(herbRow) + Integer.toString(i), "Herb", 0, "Green", 1, "Basil");
                //Herb.herbs.add(herb);
                garden[herbRow][i] = "He";
            }
        }
    }


}
