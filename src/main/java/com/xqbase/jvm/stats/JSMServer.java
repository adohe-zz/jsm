package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.servlet.SinkServlet;
import com.xqbase.jvm.stats.servlet.StatsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
        try {
            ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            handler.setContextPath("/");

            handler.addServlet(new ServletHolder(new StatsServlet()), "/stats");
            handler.addServlet(new ServletHolder(new SinkServlet()), "/sinks");

            this.server.setHandler(handler);
        } catch (Exception e) {
            logger.error("Exception in configuring servlet handlers", e);
        }
    }

    public synchronized void start() {
        if (!this.running) {
            try {
                this.server.start();
                this.running = true;
                this.statsBroadcaster.addStatsSink(new InternalStatsSink());
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
                this.statsBroadcaster.shutdown();
            } catch (Exception e) {
                logger.error("Exception in shutdown");
            }
        }
    }

    public synchronized void addStatsSink(StatsSink sink) {
        if (this.running) {
            this.statsBroadcaster.addStatsSink(sink);
        }
    }
}
