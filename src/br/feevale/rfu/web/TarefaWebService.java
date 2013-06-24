package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.feevale.rfu.model.Prato;
import br.feevale.rfu.model.Tarefa;
import br.feevale.rfu.service.TarefaService;

@WebService
public class TarefaWebService extends WebServiceInterface implements TarefaService {

	@Override
	@WebMethod
	public void save(@WebParam(name="tarefa") Tarefa tarefa) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (tarefa.getIdTarefa() == null) {
			query.append("INSERT INTO Tarefas (idPrato, descricao) ");
			query.append("VALUES (?, ?) ");
			
			parameters.add(tarefa.getIdPrato());
			parameters.add(tarefa.getDescricao());
		} else {
			query.append("UPDATE Tarefas SET ");
			query.append(" idPrato = ? ");
			query.append(",descricao = ? ");
			query.append(" WHERE idTarefa = ? ");
			
			parameters.add(tarefa.getIdPrato());
			parameters.add(tarefa.getDescricao());
			parameters.add(tarefa.getIdTarefa());
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			tarefa.setIdTarefa(id);
		}
	}

	@Override
	@WebMethod
	public Tarefa[] listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Tarefas");
			ResultSet rs = stm.getResultSet();
			
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			while (rs.next()) {
				tarefas.add(createTarefa(rs));
			}
			
			closeStatement(stm);
			
			return tarefas.toArray(new Tarefa[tarefas.size()]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@WebMethod
	public Tarefa[] getTarefas(Prato prato) {
		try {
			List<Object> parameters = new ArrayList<Object>();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM Tarefas ");
			query.append("WHERE idPrato = ? ");
			parameters.add(prato.getIdPrato());
			
			PreparedStatement stm = executeQuery(query.toString(), parameters);
			ResultSet rs = stm.getResultSet();
			
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			while (rs.next()) {
				tarefas.add(createTarefa(rs));
			}
			
			closeStatement(stm);
			
			return tarefas.toArray(new Tarefa[tarefas.size()]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@WebMethod(exclude=true)
	protected Tarefa createTarefa(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		tarefa.setIdTarefa(rs.getInt("idTarefa"));
		tarefa.setIdPrato(rs.getInt("idPrato"));
		tarefa.setDescricao(rs.getString("descricao"));
		
		return tarefa;
	}

}
