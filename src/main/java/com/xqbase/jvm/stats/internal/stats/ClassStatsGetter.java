package com.xqbase.jvm.stats.internal.stats;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * Getter for getting class stats.
 *
 * @author Tony He
 */
public class ClassStatsGetter implements StatsGetter {

    private ClassLoadingMXBean mb = ManagementFactory.getClassLoadingMXBean();

    @Override
    public Stats get() {
        ClassStats s = new ClassStats();
        s.setCurrentClassCount(mb.getLoadedClassCount());
        s.setBeenLoadedClassCount(mb.getTotalLoadedClassCount());
        s.setBeenUnloadedClassCount(mb.getUnloadedClassCount());
        return s;
    }
}
