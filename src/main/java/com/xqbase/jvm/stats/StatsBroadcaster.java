package com.xqbase.jvm.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

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

    public StatsBroadcaster() {
        executorService = new ScheduledThreadPoolExecutor(5, new DaemonThreadFactory());
    }

    // Add one StatSink to the broadcaster
    public void addStatsSink(StatsSink sink) {
        if (sink != null) {
            sinks.add(sink);
            startBroadcastToSink(sink);
        }
    }

    //TODO: find a more convenient way to add multiple sink
    public void startBroadcastToSink(final StatsSink sink) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                /*try {
                    for (StatsGetter getter : sink.getGetters()) {
                        Stats s = getter.get();
                        for (StatsHandler handler : sink.getHandlers()) {
                            handler.process(s);
                        }
                    }
                } catch (Throwable e) {
                    logger.error("Encounter an error while reporting", e);
                }*/
                sink.run();
            }
        }, sink.getInterval(), sink.getInterval(), TimeUnit.MILLISECONDS);
    }

    public void close() {
        for (StatsSink sink : sinks) {
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
