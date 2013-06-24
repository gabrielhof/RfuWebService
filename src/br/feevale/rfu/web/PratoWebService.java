package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.feevale.rfu.model.Prato;
import br.feevale.rfu.service.PratoService;

public class PratoWebService extends WebServiceInterface implements PratoService {

	@Override
	public void save(Prato prato) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (prato.getIdPrato() == null) {
			query.append("INSERT INTO Prato (nome, descricao, valor) ");
			query.append("VALUES (?, ?, ?) ");
			
			parameters.add(prato.getNome());
			parameters.add(prato.getDescricao());
			parameters.add(prato.getValor());
		} else {
			query.append("UPDATE Prato SET ");
			query.append(" nome = ? ");
			query.append(",descricao = ? ");
			query.append(",valor = ? ");
			query.append(" WHERE idPrato = ? ");
			
			parameters.add(prato.getNome());
			parameters.add(prato.getDescricao());
			parameters.add(prato.getValor());
			parameters.add(prato.getIdPrato());
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			prato.setIdPrato(id);
		}
	}

	@Override
	public Prato[] listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Prato");
			ResultSet rs = stm.getResultSet();
			
			List<Prato> pratos = new ArrayList<Prato>();
			while (rs.next()) {
				pratos.add(createPrato(rs));
			}
			
			closeStatement(stm);
			
			return pratos.toArray(new Prato[pratos.size()]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Prato createPrato(ResultSet rs) {
		Prato prato = new Prato();
		return prato;
	}

}
