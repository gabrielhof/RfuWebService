package br.feevale.rfu.main;

import javax.xml.ws.Endpoint;

import br.feevale.rfu.settings.Settings;
import br.feevale.rfu.web.RfuWebService;

public class WebServerBootstrap {

	private static Settings settings = Settings.getDefault();
	private static final String protocol;
	private static final String address;
	private static final String port;
	private static final String name;
	
	static {
		protocol = settings.getProperty("ws.protocol");
		address = settings.getProperty("ws.address");
		port = settings.getProperty("ws.port");
		name = settings.getProperty("ws.name");
	}
	
	public static void main(String[] args) {
		String address = buildAddress();
		
		Endpoint.publish(address, new RfuWebService());
		System.out.println(String.format("Published WebService to %s", address));
	}
	
	protected static String buildAddress() {
		return String.format("%s://%s:%s/%s", protocol, address, port, name);
	}
}
