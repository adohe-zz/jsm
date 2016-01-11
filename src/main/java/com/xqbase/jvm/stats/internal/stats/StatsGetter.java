package com.xqbase.jvm.stats.internal.stats;

/**
 * Interface for get one kind of jvm stats.
 *
 * @author Tony He
 */
public interface StatsGetter {

    /**
     * All stats getters must implement this method
     * to return corresponding stats.
     */
    Stats get();
}
