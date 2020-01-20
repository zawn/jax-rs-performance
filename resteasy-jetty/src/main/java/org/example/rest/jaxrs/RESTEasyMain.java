package org.example.rest.jaxrs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class RESTEasyMain {
	static final String CONTEXT_ROOT = "/";
	String host = "0.0.0.0";
	int port = 8080;
	
	public RESTEasyMain(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		final Server server = new Server(port);

		// Setup the basic Application "context" at "/".
		// This is also known as the handler tree (in Jetty speak).
		final ServletContextHandler context = new ServletContextHandler(server, CONTEXT_ROOT);

		// Setup RESTEasy's HttpServletDispatcher at "/api/*".
		final ServletHolder restEasyServlet = new ServletHolder(new HttpServletDispatcher());
		restEasyServlet.setInitParameter("resteasy.servlet.mapping.prefix", "/");
		restEasyServlet.setInitParameter("javax.ws.rs.Application", "org.example.rest.jaxrs.MyApplication");
		context.addServlet(restEasyServlet, "/*");

		// Setup the DefaultServlet at "/".
		final ServletHolder defaultServlet = new ServletHolder(new DefaultServlet());
		context.addServlet(defaultServlet, CONTEXT_ROOT);
		server.setHandler(context);
		server.start();
		server.join();
	}

	public static void main(String[] args) throws Exception {
		String host = "0.0.0.0";
		int port = 8080;
		if (args.length > 0) {
			host = args[0];
		}
		if (args.length > 1) {
			port = Integer.parseInt(args[1]);
		}

		try {
			new RESTEasyMain(host, port).run();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
