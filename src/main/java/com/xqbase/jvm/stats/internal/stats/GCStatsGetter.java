package com.xqbase.jvm.stats.internal.stats;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

/**
 * Getter for getting gc stats.
 *
 * @author Tony He
 */
public class GCStatsGetter implements StatsGetter {

    private String[] youngGenCollectorNames = { "Copy", "ParNew", "PS Scavenge", "Garbage collection optimized for short pausetimes Young Collector", "Garbage collection optimized for throughput Young Collector", "Garbage collection optimized for deterministic pausetimes Young Collector" };

    private String[] oldGenCollectorNames = { "MarkSweepCompact", "PS MarkSweep", "ConcurrentMarkSweep", "Garbage collection optimized for short pausetimes Old Collector", "Garbage collection optimized for throughput Old Collector", "Garbage collection optimized for deterministic pausetimes Old Collector" };

    private List<String> young = Arrays.asList(this.youngGenCollectorNames);
    private List<String> old = Arrays.asList(this.oldGenCollectorNames);

    private GCStats previous = new GCStats();

    @Override
    public Stats get() {
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        int minorGcCount = 0; int fullGcCount = 0; int otherGcCount = 0;
        long minorGcTime = 0L; long fullGcTime = 0L; long otherGcTime = 0L;
        for (GarbageCollectorMXBean b : beans) {
            String name = b.getName();
            if (this.young.contains(name)) {
                minorGcCount = (int)(minorGcCount + b.getCollectionCount());
                minorGcTime += b.getCollectionTime();
            } else if (this.old.contains(name)) {
                fullGcCount = (int)(fullGcCount + b.getCollectionCount());
                fullGcTime += b.getCollectionTime();
            } else {
                otherGcCount = (int)(otherGcCount + b.getCollectionCount());
                otherGcTime += b.getCollectionTime();
            }
        }

        GCStats s = new GCStats();
        s.setMinorGcCount(minorGcCount - this.previous.getMinorGcCount());
        s.setMinorGcTime(minorGcTime - this.previous.getMinorGcTime());
        s.setFullGcCount(fullGcCount - this.previous.getFullGcCount());
        s.setFullGcTime(fullGcTime - this.previous.getFullGcTime());
        s.setOtherGcCount(otherGcCount - this.previous.getOtherGcCount());
        s.setOtherGcCount(otherGcTime - this.previous.getOtherGcTime());

        this.previous.setMinorGcCount(minorGcCount);
        this.previous.setMinorGcTime(minorGcTime);
        this.previous.setFullGcCount(fullGcCount);
        this.previous.setFullGcTime(fullGcTime);
        this.previous.setOtherGcCount(otherGcCount);
        this.previous.setOtherGcCount(otherGcTime);

        return s;
    }
}
