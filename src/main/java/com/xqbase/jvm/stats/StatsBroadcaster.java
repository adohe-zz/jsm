package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * StatsBroadcaster knows how to gather stats and send them
 * to any StatsSink.
 *
 * @author Tony He
 */
public class StatsBroadcaster {

    private static final Logger logger = LoggerFactory.getLogger(StatsBroadcaster.class);

    private List<StatsSink> sinks = new ArrayList<StatsSink>();
    private ScheduledExecutorService executorService;

    private List<StatsGetter> getters = new CopyOnWriteArrayList<StatsGetter>();

    public StatsBroadcaster() {
        // Add builtin stats getters.
        this.getters.add(new ClassStatsGetter());
        this.getters.add(new GCStatsGetter());
        this.getters.add(new MemoryStatsGetter());
        this.getters.add(new OSStatsGetter());
        this.getters.add(new ThreadStatsGetter());

        executorService = new ScheduledThreadPoolExecutor(2, new DaemonThreadFactory());
    }

    // Add one StatSink to the broadcaster
    public void addStatsSink(StatsSink sink) {
        if (sink != null) {
            sinks.add(sink);
            recordStatsToSink(sink);
        }
    }

    private void recordStatsToSink(final StatsSink sink) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    for (StatsGetter getter : StatsBroadcaster.this.getters) {
                        Stats s = getter.get();
                        sink.process(s);
                    }
                } catch (Throwable e) {
                    logger.error("Encounter an error while reporting to sink: " + sink.getName(), e);
                }
            }
        }, sink.getInterval(), sink.getInterval(), TimeUnit.SECONDS);
    }

    public void shutdown() {
        for (StatsSink sink : sinks) {
            logger.info("close sink: " + sink.getName());
            sink.shutdown();
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}
