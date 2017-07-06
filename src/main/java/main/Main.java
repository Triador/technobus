package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.DBService;
import servlets.FromServlet;
import servlets.ToServlet;

/**
 * Created by antonandreev on 06/07/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = DBService.getInstance();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new FromServlet(dbService)), "/from");
        contextHandler.addServlet(new ServletHolder(new ToServlet(dbService)), "/to");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("web");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.join();
        server.start();
    }
}
