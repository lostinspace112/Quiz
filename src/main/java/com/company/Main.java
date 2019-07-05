package com.company;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Scanner;

import static com.company.util.MemoryLeakChecker.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int totalSecs, samplerSecs;
        String processName;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of run seconds: ");
        totalSecs = scanner.nextInt();
        System.out.println("Enter number of seconds for sampler: ");
        samplerSecs = scanner.nextInt();
        System.out.println("Enter process name: ");
        processName = scanner.toString();

        Instant now = Instant.now();
        Instant sampleCheckpoint = now.plusSeconds(samplerSecs);
        Instant finishExecution = now.plusSeconds(totalSecs);

        final Sampler sampler = new Sampler(processName);
        LinkedList<Double> cpuPercentages = new LinkedList<>();
        LinkedList<Double> privateMemories = new LinkedList<>();
        LinkedList<Double> numberOfFileDescriptors = new LinkedList<>();

        cpuPercentages.add(sampler.getCpuPercentage());
        privateMemories.add(sampler.getPrivateMemory());
        numberOfFileDescriptors.add(sampler.getNumberOfFileDescriptors());

        do {
            now = Instant.now();
            if (now.isAfter(sampleCheckpoint)) {
                Double previousCpuPercentages = cpuPercentages.getLast();
                Double previousPrivateMemory = privateMemories.getLast();
                Double previousFileDescriptors = numberOfFileDescriptors.getLast();

                sampleCheckpoint = sampleCheckpoint.plusSeconds(samplerSecs);
                cpuPercentages.add(sampler.getCpuPercentage());
                privateMemories.add(sampler.getPrivateMemory());
                numberOfFileDescriptors.add(sampler.getNumberOfFileDescriptors());

                checkCpuPercentage(previousCpuPercentages, cpuPercentages.getLast());
                checkFileDescriptors(previousFileDescriptors, numberOfFileDescriptors.getLast());
                checkPrivateMemory(previousPrivateMemory, privateMemories.getLast());
            }


        } while (now.isBefore(finishExecution));


        Double averageCpuUsage = cpuPercentages.stream().mapToDouble(val -> val).average().orElse(0.0);
        Double averageFileDescriptors = numberOfFileDescriptors.stream().mapToDouble(val -> val).average().orElse(0.0);
        Double averagePrivateMemory = privateMemories.stream().mapToDouble(val -> val).average().orElse(0.0);

        System.out.println("The average cpu usage is " + averageCpuUsage);
        System.out.println("The average number of File descriptors " + averageFileDescriptors);
        System.out.println("The average use of memory " + averagePrivateMemory);

    }
}
