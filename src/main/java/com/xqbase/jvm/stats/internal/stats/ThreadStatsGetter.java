package com.xqbase.jvm.stats.internal.stats;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Getter for getting thread stats.
 *
 * @author Tony He
 */
public class ThreadStatsGetter implements StatsGetter {

    private ThreadMXBean mb = ManagementFactory.getThreadMXBean();

    @Override
    public Stats get() {
        ThreadStats s = new ThreadStats();
        s.setCurrentThreadCount(mb.getThreadCount());
        s.setDaemonThreadCount(mb.getDaemonThreadCount());
        s.setBeenCreatedThreadCount(mb.getTotalStartedThreadCount());
        return null;
    }
}
