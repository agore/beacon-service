package org.bitxbit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class.
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("jersey.config.server.provider.classnames", BeaconEventResource.class.getCanonicalName());
        initParams.put("javax.ws.rs.Application", JerseyApplication.class.getCanonicalName());
        jerseyServlet.setInitParameters(initParams);

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}

