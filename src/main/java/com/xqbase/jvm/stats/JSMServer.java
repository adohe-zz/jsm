package com.xqbase.jvm.stats;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main JSM server.
 *
 * @author Tony He
 */
public class JSMServer {

    private static final Logger logger = LoggerFactory.getLogger(JSMServer.class);

    private volatile boolean running = false;
    private Server server;

    private StatsBroadcaster statsBroadcaster;

    public JSMServer(int port) {
        this.statsBroadcaster = new StatsBroadcaster();

        this.server = new Server(port);
    }

    public synchronized void start() {
        if (!this.running) {
            try {
                this.server.start();
                this.running = true;
            } catch (Exception e) {
                logger.error("Exception in starting");
            }
        }
    }

    public synchronized void shutdown() {
        if (this.running) {
            try {
                this.server.stop();
                this.running = true;
            } catch (Exception e) {
                logger.error("Exception in shutdown");
            }
        }
    }

    public void addStatsSink(StatsSink sink) {
        this.statsBroadcaster.addStatsSink(sink);
    }
}
