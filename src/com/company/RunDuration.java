package com.company;

import com.company.util.CPUTimer;
import com.company.util.Memory;
import com.company.util.OpenFileCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class RunDuration {
    private int globalTimer = 0;
    public static void main(String[] args) throws IOException {
        new RunDuration();
    }
    public RunDuration() throws IOException {
        Timer timer = new Timer();
        Scanner limitTimer = new Scanner(System.in);
        double totalSecs, samplerSecs;
        System.out.println("Enter number of run seconds: ");
        totalSecs = limitTimer.nextInt();
        Scanner sampler = new Scanner(System.in);
        System.out.println("Enter number of seconds for sampler: ");
        samplerSecs = limitTimer.nextInt();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                globalTimer++;
                try {
                    String process;
                    Process p = Runtime.getRuntime().exec("ps -few");
                    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    Scanner samplingInterval = new Scanner(System.in);
                    while ((process = input.readLine()) != null) ;
                    {
                        System.out.println(process);
                        CPUTimer getProcessCpuLoad = new CPUTimer();
                        getProcessCpuLoad.processCpuLoad();
                        OpenFileCount newFileCount = new OpenFileCount();
                        newFileCount.fileCount();
                        newFileCount.methodHandles();
                        Memory newMmoryConsumption = new Memory();
                        newMmoryConsumption.memoryConsuption();
                    }
                    input.close();
                } catch (Exception err) {
                    err.printStackTrace();
                }

                System.out.println("running");
                if (globalTimer == totalSecs) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}





