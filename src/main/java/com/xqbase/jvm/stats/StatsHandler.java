package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.Stats;

/**
 * Process stats handler.
 *
 * @author Tony He
 */
public interface StatsHandler {

    //  process is the only method
    void process(Stats stats);
}
