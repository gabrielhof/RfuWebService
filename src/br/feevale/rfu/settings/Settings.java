package br.feevale.rfu.settings;

import java.util.Properties;

public class Settings {

	private static Settings defaultSettings;
	
	private Properties properties = new Properties();
	
	private Settings() {
		try {
			properties.load(Resources.getResourceStream(Resources.SETTINGS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static Settings getDefault() {
		if (defaultSettings == null) {
			defaultSettings = new Settings();
		}
		
		return defaultSettings;
	}
}
