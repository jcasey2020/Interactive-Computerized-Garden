package com.example.finalproject_jacksversion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Random;


public class PestControl {
    //Pest control searches for bugs in every cell.
    // The system has a 50% success rate


    public static void killSpiderMite(){

        for (int i = 0; i< Controller.getGardenGrid().getRowCount(); i++){
            for (int j=0;j<Controller.getGardenGrid().getColumnCount();j++){
                Random ran = new Random();
                int rando = ran.nextInt(2);
                if (rando == 1){
                    //Plant.plantsList.remove(Insect); //GO OVER TODAY
                }

            }
        }
    }
}
