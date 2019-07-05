package com.company.util;

public class MemoryLeakChecker {

    private static final double MEMORY_LEAK_PERCENT = 0.2;

    public static void checkCpuPercentage(double previousRun, double currentRun) {
        if ((currentRun * MEMORY_LEAK_PERCENT) > previousRun ) {
            System.out.println("Cpu Increased with more that 80% and it is now" + currentRun);
        }
    }

    public static void checkPrivateMemory(double previousRun, double currentRun) {
        if ((currentRun * MEMORY_LEAK_PERCENT) > previousRun) {
            throw new RuntimeException("Private memory increased from previous run with more than 80%");
        }
    }

    public static void checkFileDescriptors(double previousRun, double currentRun) {
        if ((currentRun * MEMORY_LEAK_PERCENT) > previousRun) {
            throw new RuntimeException("file descriptors increased from previous run with more than 80%");
        }
    }
}
