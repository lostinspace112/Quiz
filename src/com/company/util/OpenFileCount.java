package com.company.util;

import java.lang.invoke.MethodHandles;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;

public class OpenFileCount{
    public static void fileCount(){
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        if(os instanceof UnixOperatingSystemMXBean){
            System.out.println("Number of open file descriptors: " + ((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());
        }
    }
    public static void methodHandles(){
        MethodHandles.Lookup lookup = MethodHandles.lookup();
            System.out.println(lookup);
    }
}
