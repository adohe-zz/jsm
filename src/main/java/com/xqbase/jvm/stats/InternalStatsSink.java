package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.*;

/**
 * Internal Stats sink records stats every one second
 * and expose the stats by servlet.
 *
 * @author Tony He
 */
public class InternalStatsSink extends StatsSink {

    protected volatile GCStats gcStats = new GCStats();
    protected volatile MemoryStats memoryStats = new MemoryStats();
    protected volatile ThreadStats threadStats = new ThreadStats();
    protected volatile ClassStats classStats = new ClassStats();
    protected volatile OSStats osStats = new OSStats();

    public InternalStatsSink() {
        super("internal_stats", 2);
    }

    @Override
    public void processClassStats(ClassStats classStats) {
        this.classStats = classStats;
    }

    @Override
    public void processGCStats(GCStats gcStats) {
        this.gcStats = gcStats;
    }

    @Override
    public void processMemoryStats(MemoryStats memoryStats) {
        this.memoryStats = memoryStats;
    }

    @Override
    public void processThreadStats(ThreadStats threadStats) {
        this.threadStats = threadStats;
    }

    @Override
    public void processOSStats(OSStats osStats) {
        this.osStats = osStats;
    }
}
