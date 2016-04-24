package com.xqbase.jvm.stats;

/**
 * StatsSink knows how to handle stats.
 *
 * @author Tony He
 */
public abstract class StatsSink extends AbstractStatsHandler {

    private volatile boolean running = true;

    private String name;
    private long interval;

    public StatsSink(String name, long interval) {
        this.name = name;
        this.interval = interval < 0 ? 5 : interval;
    }

    public String getName() {
        return name;
    }

    public long getInterval() {
        return interval;
    }

    // This gives you extra chance to close internal structs.
    public void shutdown() {
        if (this.running) {
            this.running = false;
        }
    }
}
