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

public class Sampler {
    private int globalTimer = 0;
    private final String processName;

    public Sampler(String processName) throws IOException {
        this.processName = processName;
    }

    public Double getCpuPercentage() {
        return null;
    }

    public Double getPrivateMemory() {
        return null;
    }

    public Double getNumberOfFileDescriptors() {
        return null;
    }

    private void whatDanDid() {
        Timer timer = new Timer();

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
//                if (globalTimer == totalSecs) {
//                    timer.cancel();
//                }
            }
        }, 0, 1000);
    }
}





