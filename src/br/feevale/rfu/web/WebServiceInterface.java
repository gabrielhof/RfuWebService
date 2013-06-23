package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.feevale.rfu.factory.ConnectionFactory;
import br.feevale.rfu.factory.ConnectionFactorySingleton;

public class WebServiceInterface {

	private static ConnectionFactory connectionFactory = ConnectionFactorySingleton.getInstance();
	
	public Integer executeSaveOrUpdate(String query, List<Object> parameters) {
		try {
			PreparedStatement stm = executeQuery(query, parameters);
			
			Integer result = null;
			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			closeResult(stm);
			
			return result;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public PreparedStatement executeQuery(String query) {
		return executeQuery(query, null);
	}
	
	public PreparedStatement executeQuery(String query, List<Object> parameters) {
		try {
			PreparedStatement stm = connectionFactory.getConnection().prepareStatement(query);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.size(); i++) {
					stm.setObject(i+1, parameters.get(i));
				}
			}
			
			stm.execute();
			return stm;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void closeResult(PreparedStatement stm) {
		try {
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
