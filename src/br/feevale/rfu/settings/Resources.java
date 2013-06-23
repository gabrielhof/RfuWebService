package br.feevale.rfu.settings;

import java.io.InputStream;

public class Resources {

	public static String SETTINGS = "settings.properties";
	
	public static InputStream getResourceStream(String resourceName) {
		return Resources.class.getResourceAsStream(resourceName);
	}
	
}
