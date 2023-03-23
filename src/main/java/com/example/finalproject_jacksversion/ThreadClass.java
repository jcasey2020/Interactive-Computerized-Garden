package com.example.finalproject_jacksversion;

public class ThreadClass {
    private Thread t;

    ThreadClass(){}




    /*private static long startTime;


    public static void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    public static long getCurrentTime(){
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime)/1000;
    }


    public static void runEveryTenSeconds(Runnable task) {
        new Thread(() -> {
            while (true) {
                long currentTime = getCurrentTime();
                long nextExecutionTime = (currentTime / 10 + 1) * 10;
                long sleepTime = (nextExecutionTime - currentTime) * 1000;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // Thread interrupted
                    break;
                }
                task.run();
            }
        }).start();
    }*/



}
