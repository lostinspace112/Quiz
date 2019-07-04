package com.company.util;

import java.lang.invoke.MethodHandles;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;

class OpenFileCount{
    public static void main(String[] args){
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        if(os instanceof UnixOperatingSystemMXBean){
            System.out.println("Number of open file descriptors: " + ((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        return lookup;
    }
}
