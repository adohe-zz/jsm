package com.xqbase.jvm.stats.internal.stats;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Getter for getting memory stats.
 *
 * @author Tony He
 */
public class MemoryStatsGetter implements StatsGetter {

    private MemoryMXBean mb = ManagementFactory.getMemoryMXBean();

    @Override
    public Stats get() {
        MemoryUsage u = mb.getHeapMemoryUsage();

        MemoryStats s = new MemoryStats();
        s.setHeapUsedMemory(u.getUsed());
        s.setHeapCommittedMemory(u.getCommitted());
        s.setHeapMaxMemory(u.getMax());

        u = mb.getNonHeapMemoryUsage();
        s.setNonHeapUsedMemory(u.getUsed());
        s.setNonHeapCommittedMemory(u.getCommitted());
        s.setNonHeapMaxMemory(u.getMax());

        return s;
    }
}
