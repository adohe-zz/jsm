package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.Stats;
import com.xqbase.jvm.stats.internal.stats.StatsGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * StatsSink knows how to handle stats.
 *
 * @author Tony He
 */
//TODO: Remove StatsGetters from stats sink
public abstract class StatsSink {

    private static final Logger logger = LoggerFactory.getLogger(StatsSink.class);

    private volatile boolean running = false;

    private String name;
    private long interval;

    private List<StatsGetter> getters = new CopyOnWriteArrayList<StatsGetter>();
    private List<StatsHandler> handlers = new CopyOnWriteArrayList<StatsHandler>();

    public StatsSink(String name) {
        this.name = name;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        if (interval > 0L) {
            this.interval = interval;
        }
    }

    public List<StatsGetter> getGetters() {
        return getters;
    }

    public List<StatsHandler> getHandlers() {
        return handlers;
    }

    public void start() {
        if (!this.running) {
            this.running = true;
        }
    }

    public void shutdown() {
        if (this.running) {
            this.running = false;
        }
    }

    public void run() {
        if (this.running) {
            try {
                for (StatsGetter getter : this.getters) {
                    Stats s = getter.get();
                    for (StatsHandler handler : this.handlers) {
                        handler.process(s);
                    }
                }
            } catch (Throwable t) {
                logger.error(name + " encounter an error while handle stats", t);
            }
        }
    }

    public abstract void addHandler(StatsHandler handler);
}
