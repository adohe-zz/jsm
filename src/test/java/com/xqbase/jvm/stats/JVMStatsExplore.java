package com.xqbase.jvm.stats;

import com.xqbase.jvm.stats.internal.stats.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple example how to integrate jsm to your Java application
 * to monitor JVM stats.
 *
 * @author Tony He
 */
public class JVMStatsExplore {

    public static void main(String[] args) throws Exception {
        Server echoServer = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        handler.addServlet(new ServletHolder(new EchoServlet()), "/echo");

        echoServer.setHandler(handler);

        // start the jsm server
        initStatsServer();

        // start the main echo server
        echoServer.start();
    }

    private static void initStatsServer() {
        JSMServer jsmServer = new JSMServer(8088);
        jsmServer.addStatsSink(new PrintStatsSink("PrintStatsSink", 2000));
        jsmServer.start();
    }

    static class EchoServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req, resp);
        }
    }

    // Stats sink that just prints stats to stdout.
    static class PrintStatsSink extends StatsSink {

        public PrintStatsSink(String name, long interval) {
            super(name, interval);
        }

        @Override
        public void processClassStats(ClassStats classStats) {
            System.out.printf("%s\n", classStats.toJsonStr());
        }

        @Override
        public void processGCStats(GCStats gcStats) {
            System.out.printf("%s\n", gcStats.toJsonStr());
        }

        @Override
        public void processMemoryStats(MemoryStats memoryStats) {
            System.out.printf("%s\n", memoryStats.toJsonStr());
        }

        @Override
        public void processThreadStats(ThreadStats threadStats) {
            System.out.printf("%s\n", threadStats.toJsonStr());
        }

        @Override
        public void processOSStats(OSStats osStats) {
            System.out.printf("%s\n", osStats.toJsonStr());
        }
    }
}
