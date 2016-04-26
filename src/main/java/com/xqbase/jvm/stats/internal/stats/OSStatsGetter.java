package com.xqbase.jvm.stats.internal.stats;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Getter for getting operating system stats.
 *
 * @author Tony He
 */
public class OSStatsGetter implements StatsGetter {

    private static OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
    private static Class<?> clazz = bean.getClass();
    private static Class statsClazz = OSStats.class;
    private static ConcurrentHashMap<String, Method> methodMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Field> fieldMap = new ConcurrentHashMap<>();

    private OSStats previous = new OSStats();

    static {
        String[] arr1 = { "committedVirtualMemory", "totalSwapSpaceSize", "freeSwapSpaceSize", "processCpuTime", "freePhysicalMemorySize", "totalPhysicalMemorySize", "systemCpuLoad", "processCpuLoad", "maxFileDescriptorCount", "openFileDescriptorCount" };

        String[] arr2 = { "getCommittedVirtualMemorySize", "getTotalSwapSpaceSize", "getFreeSwapSpaceSize", "getProcessCpuTime", "getFreePhysicalMemorySize", "getTotalPhysicalMemorySize", "getSystemCpuLoad", "getProcessCpuLoad", "getMaxFileDescriptorCount", "getOpenFileDescriptorCount" };

        for (int i = 0; i < arr1.length; i++) {
            String fieldName = arr1[i];
            try {
                Method m = clazz.getMethod(arr2[i], new Class[0]);
                m.setAccessible(true);
                methodMap.putIfAbsent(fieldName, m);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                Field f = statsClazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                fieldMap.putIfAbsent(fieldName, f);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Stats get() {
        OSStats s = new OSStats();
        for (Map.Entry<String, Method> entry : methodMap.entrySet()) {
            String fieldName = entry.getKey();
            Method m = entry.getValue();
            Field f = fieldMap.get(fieldName);
            if (f != null && m != null) {
                try {
                    f.set(s, m.invoke(bean));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long processCpuTime = s.getProcessCpuTime();
        s.setProcessCpuTime(processCpuTime - this.previous.getProcessCpuTime());
        this.previous.setProcessCpuTime(processCpuTime);

        return s;
    }
}
