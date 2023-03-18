package com.example.finalproject_jacksversion;

public class Timer {
    private static long startTime;


    public static void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    public static long getCurrentTime(){
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime)/1000;
    }


}
