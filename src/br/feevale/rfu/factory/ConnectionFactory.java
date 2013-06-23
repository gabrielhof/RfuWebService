package br.feevale.rfu.factory;

import java.sql.Connection;

public interface ConnectionFactory {

	public Connection getConnection();
	public void close();
	
}
