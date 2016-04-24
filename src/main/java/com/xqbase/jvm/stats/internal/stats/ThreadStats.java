package com.xqbase.jvm.stats.internal.stats;

/**
 * Thread stats of jvm.
 *
 * @author Tony He
 */
public class ThreadStats implements Stats {

    private int currentThreadCount;
    private int daemonThreadCount;
    private long beenCreatedThreadCount;

    public int getCurrentThreadCount() {
        return currentThreadCount;
    }

    public void setCurrentThreadCount(int currentThreadCount) {
        this.currentThreadCount = currentThreadCount;
    }

    public int getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public void setDaemonThreadCount(int daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public long getBeenCreatedThreadCount() {
        return beenCreatedThreadCount;
    }

    public void setBeenCreatedThreadCount(long beenCreatedThreadCount) {
        this.beenCreatedThreadCount = beenCreatedThreadCount;
    }

    @Override
    public String toJsonStr() {
        return "{\"currentThreadCount\":\"" + this.currentThreadCount + "\", \"daemonThreadCount\":\"" +
            this.daemonThreadCount + "\", \"beenCreatedThreadCount\":\"" + this.beenCreatedThreadCount + "\"}";
    }
}
