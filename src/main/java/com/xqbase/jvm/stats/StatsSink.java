package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.Stats;
import com.xqbase.jvm.stats.internal.stats.StatsGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

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
