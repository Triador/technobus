package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import services.SheetsService;
import services.SheetsServiceImpl;
import servlets.getScheduleServlet;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * Created by antonandreev on 06/07/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SheetsService sheetsService = SheetsServiceImpl.getInstance();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new getScheduleServlet(sheetsService)), "/schedule");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/web");

        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        WebAppContext webapp = new WebAppContext();
        webapp.setWar(location.toExternalForm());

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, contextHandler, webapp});

        Server server = new Server(Integer.parseInt(args[0]));
        server.setHandler(handlers);

        server.join();
        server.start();
    }
}
