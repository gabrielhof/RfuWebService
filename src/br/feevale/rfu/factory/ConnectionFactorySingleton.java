package br.feevale.rfu.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.feevale.rfu.settings.Settings;

public class ConnectionFactorySingleton implements ConnectionFactory {

	private static Settings settings = Settings.getDefault();
	private static ConnectionFactorySingleton INSTANCE;
	
	private Connection connection;
	
	private ConnectionFactorySingleton() {
		try {
			Class.forName(settings.getProperty("jdbc.driver"));
			connection = DriverManager.getConnection(settings.getProperty("jdbc.url"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Connection getConnection() {
		return connection;
	}
	
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionFactorySingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionFactorySingleton();
		}
		
		return INSTANCE;
	}

}
