package com.xqbase.jvm.stats.internal.stats;

/**
 * Memory stats of jvm.
 *
 * @author Tony He
 */
public class MemoryStats implements Stats {

    private long heapUsedMemory;
    private long heapCommittedMemory;
    private long heapMaxMemory;
    private long nonHeapUsedMemory;
    private long nonHeapCommittedMemory;
    private long nonHeapMaxMemory;

    public long getHeapUsedMemory() {
        return heapUsedMemory;
    }

    public void setHeapUsedMemory(long heapUsedMemory) {
        this.heapUsedMemory = heapUsedMemory;
    }

    public long getHeapCommittedMemory() {
        return heapCommittedMemory;
    }

    public void setHeapCommittedMemory(long heapCommittedMemory) {
        this.heapCommittedMemory = heapCommittedMemory;
    }

    public long getHeapMaxMemory() {
        return heapMaxMemory;
    }

    public void setHeapMaxMemory(long heapMaxMemory) {
        this.heapMaxMemory = heapMaxMemory;
    }

    public long getNonHeapUsedMemory() {
        return nonHeapUsedMemory;
    }

    public void setNonHeapUsedMemory(long nonHeapUsedMemory) {
        this.nonHeapUsedMemory = nonHeapUsedMemory;
    }

    public long getNonHeapCommittedMemory() {
        return nonHeapCommittedMemory;
    }

    public void setNonHeapCommittedMemory(long nonHeapCommittedMemory) {
        this.nonHeapCommittedMemory = nonHeapCommittedMemory;
    }

    public long getNonHeapMaxMemory() {
        return nonHeapMaxMemory;
    }

    public void setNonHeapMaxMemory(long nonHeapMaxMemory) {
        this.nonHeapMaxMemory = nonHeapMaxMemory;
    }

    @Override
    public String toJsonStr() {
        return null;
    }
}
