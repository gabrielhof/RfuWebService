package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.feevale.rfu.model.Prato;
import br.feevale.rfu.model.list.Pratos;
import br.feevale.rfu.service.PratoService;

@Path("/Prato")
@Produces(MediaType.APPLICATION_XML)
public class PratoWebService extends WebServiceInterface implements PratoService {

	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
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

	@POST
	@Path("listAll")
	@Override
	public Pratos listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Prato");
			ResultSet rs = stm.getResultSet();
			
			Pratos pratos = new Pratos();
			while (rs.next()) {
				pratos.add(createPrato(rs));
			}
			
			closeStatement(stm);
			
			return pratos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Prato createPrato(ResultSet rs) {
		Prato prato = new Prato();
		return prato;
	}

}
