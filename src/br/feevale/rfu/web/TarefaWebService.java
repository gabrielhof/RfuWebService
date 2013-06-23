package br.feevale.rfu.web;

import java.util.ArrayList;
import java.util.List;

import br.feevale.rfu.model.Prato;
import br.feevale.rfu.model.Tarefa;
import br.feevale.rfu.service.TarefaService;

public class TarefaWebService extends WebServiceInterface implements TarefaService {

	@Override
	public void save(Tarefa tarefa) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (tarefa.getIdTarefa() == null) {
			query.append("INSERT INTO Tarefas (idPrato, descricao) ");
			query.append("VALUES (?, ?, ?) ");
			
			parameters.add(tarefa.getIdPrato());
			parameters.add(tarefa.getDescricao());
		} else {
			query.append("UPDATE Tarefas SET ");
			query.append(" idPrato = ? ");
			query.append(",descricao = ? ");
			query.append(" WHERE idTarefa = ? ");
			
			parameters.add(tarefa.getIdPrato());
			parameters.add(tarefa.getDescricao());
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			tarefa.setIdTarefa(id);
		}
	}

	@Override
	public Tarefa[] listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tarefa[] getTarefas(Prato prato) {
		// TODO Auto-generated method stub
		return null;
	}

}
