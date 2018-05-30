import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/** Main entry into program. */
public class JavaServer {

	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/"); 
		Server jettyServer = new Server(8080);
		
		jettyServer.setHandler(context);
		
		ServletHolder jerseyServlet = context.addServlet(GameOfLife.class, "/*");

		jerseyServlet.setInitOrder(0);

		FilterHolder filterHolder = context.addFilter(org.eclipse.jetty.servlets.CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		filterHolder.setInitParameter("allowedOrigins", "*");
		filterHolder.setInitParameter("allowedMethods", "OPTIONS,PUT");
		filterHolder.setInitParameter("allowedHeaders", "content-type");
		CrossOriginFilter corsFilter = new CrossOriginFilter();
		filterHolder.setFilter(corsFilter);
		
		try { 
			jettyServer.start(); jettyServer.join(); 
		} catch (Exception e) { 
			System.out.print("Error running ServerConfig service" + e); 
		} finally {
			jettyServer.destroy();
		}
		
	}

}
