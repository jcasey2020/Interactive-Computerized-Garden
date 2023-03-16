package com.example.finalproject_jacksversion;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden();
        garden.populateGarden();
        garden.AddFlowers();
        garden.AddFruit();
        garden.printGarden();
    }
}
