package org.ozmi.aroundsee.server.core;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.ozmi.aroundsee.server.services.LoginService;
import org.ozmi.aroundsee.server.services.GoogleService;
import org.ozmi.aroundsee.server.services.SampleService;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
    	Config config = ConfigFactory.load();
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.setAttribute("config", config);

		Server jettyServer = new Server(config.getInt("server.port"));
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				SampleService.class.getCanonicalName() + ";" + LoginService.class.getCanonicalName() + ";" +
				GoogleService.class.getCanonicalName());

		try {
			System.out.println("----- server is running, port: 8080");
			jettyServer.start();
			jettyServer.join();
		} catch (Exception ex){
			jettyServer.destroy();
		} finally {
			jettyServer.destroy();
		}
    }
}
