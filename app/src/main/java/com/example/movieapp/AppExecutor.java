package com.example.movieapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AppExecutor {
    private static AppExecutor instance;

    public static AppExecutor getInstance(){
        if (instance == null){
            instance = new AppExecutor();
        }
        return instance;
    }

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService scheduledExecutorService(){
        return scheduledExecutorService;
    }
}
