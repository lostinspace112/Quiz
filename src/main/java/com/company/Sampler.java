package com.company;

import com.sun.management.UnixOperatingSystemMXBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class Sampler {

    private final String processName;

    public Sampler(String processName) throws IOException {
        this.processName = processName;
    }

    public Double getCpuPercentage() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

        if (list.isEmpty()) return Double.NaN;

        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();

        // usually takes a couple of seconds before we get real values
        if (value == -1.0) return Double.NaN;
        // returns a percentage value with 1 decimal point precision
        return ((int) (value * 1000) / 10.0);
    }

    public Double getPrivateMemory() {
        Runtime r = Runtime.getRuntime();
        Double g = Double.valueOf((r.totalMemory() - r.freeMemory()));
       return g;
    }

    public Double getNumberOfFileDescriptors() {
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        Double gg = Double.valueOf(((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());
        return gg;
    }
}





