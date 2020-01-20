package org.example.rest.jaxrs;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

public class RESTEasyUndertowMain {
	static final String CONTEXT_ROOT = "/";
	String host = "0.0.0.0";
	int port = 8080;
	
	public RESTEasyUndertowMain(String host, int port) {
		this.host = host;
		this.port = port;
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

		Undertow.Builder builder = Undertow.builder().addHttpListener(port, host);
		UndertowJaxrsServer server = new UndertowJaxrsServer().start(builder);
		server.deploy(MyApplication.class, "/");
	}
}
