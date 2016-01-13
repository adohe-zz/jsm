package com.xqbase.jvm.stats.internal.stats;

/**
 * Operating Systems Stats.
 *
 * @author Tony He
 */
public class OSStats implements Stats {

    private long committedVirtualMemory;
    private long totalSwapSpaceSize;
    private long freeSwapSpaceSize;
    private long processCpuTime;
    private long freePhysicalMemorySize;
    private long totalPhysicalMemorySize;
    private double systemCpuLoad;
    private double processCpuLoad;
    private long maxFileDescriptorCount;
    private long openFileDescriptorCount;

    public long getCommittedVirtualMemory() {
        return committedVirtualMemory;
    }

    public void setCommittedVirtualMemory(long committedVirtualMemory) {
        this.committedVirtualMemory = committedVirtualMemory;
    }

    public long getOpenFileDescriptorCount() {
        return openFileDescriptorCount;
    }

    public void setOpenFileDescriptorCount(long openFileDescriptorCount) {
        this.openFileDescriptorCount = openFileDescriptorCount;
    }

    public long getMaxFileDescriptorCount() {
        return maxFileDescriptorCount;
    }

    public void setMaxFileDescriptorCount(long maxFileDescriptorCount) {
        this.maxFileDescriptorCount = maxFileDescriptorCount;
    }

    public double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

    public long getTotalPhysicalMemorySize() {
        return totalPhysicalMemorySize;
    }

    public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
        this.totalPhysicalMemorySize = totalPhysicalMemorySize;
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public long getProcessCpuTime() {
        return processCpuTime;
    }

    public void setProcessCpuTime(long processCpuTime) {
        this.processCpuTime = processCpuTime;
    }

    public long getFreeSwapSpaceSize() {
        return freeSwapSpaceSize;
    }

    public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
        this.freeSwapSpaceSize = freeSwapSpaceSize;
    }

    public long getTotalSwapSpaceSize() {
        return totalSwapSpaceSize;
    }

    public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
        this.totalSwapSpaceSize = totalSwapSpaceSize;
    }

    @Override
    public String toJsonStr() {
        return null;
    }
}
