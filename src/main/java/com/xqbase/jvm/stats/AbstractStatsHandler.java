package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.*;

/**
 * AbstractStatsHandler implements process function and calls
 * different sub routine for kinds of stats.
 *
 * @author Tony He
 */
public abstract class AbstractStatsHandler implements StatsHandler {

    @Override
    public void process(Stats stats) {
        if (stats == null)
            return;

        if (stats instanceof ClassStats) {
            processClassStats((ClassStats)stats);
        } else if (stats instanceof GCStats) {
            processGCStats((GCStats)stats);
        } else if (stats instanceof MemoryStats) {
            processMemoryStats((MemoryStats)stats);
        } else if (stats instanceof ThreadStats) {
            processThreadStats((ThreadStats)stats);
        } else {
            processOSStats((OSStats)stats);
        }
    }

    public abstract void processClassStats(ClassStats classStats);

    public abstract void processGCStats(GCStats gcStats);

    public abstract void processMemoryStats(MemoryStats memoryStats);

    public abstract void processThreadStats(ThreadStats threadStats);

    public abstract void processOSStats(OSStats osStats);
}
