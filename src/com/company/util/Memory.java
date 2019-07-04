package com.company.util;

import java.time.Duration;
import java.time.Instant;

public class Memory {

    long startTime = System.currentTimeMillis();
        System.out.println("Hello");

    Instant firstInstant = Instant.ofEpochSecond(1294881180);
    Instant secondInstant = Instant.ofEpochSecond(1294708260);

    Duration between = Duration.between(firstInstant, secondInstant);

        System.out.println(between);

    Runtime r=Runtime.getRuntime();

        System.out.println("No of Processor: "+
                r.availableProcessors());
        System.out.println();
        System.out.println("Total memory: "+r.totalMemory());
        System.out.println("Free memory: "+r.freeMemory());
        System.out.println("Memory occupied: "+
                (r.totalMemory()-r.freeMemory()));

        for(int i=0;i<=10000;i++){
        new Object();
    }

        r.gc();

        System.out.println("::Memory status::");
        System.out.println("Total memory: "+r.totalMemory());
        System.out.println("Free memory: "+r.freeMemory());
        System.out.println("Memory occupied: "+
                (r.totalMemory()-r.freeMemory()));
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
        System.out.println(totalTime);

}
}
