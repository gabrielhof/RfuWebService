package br.feevale.rfu.web;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.feevale.rfu.model.TarefasPendente;
import br.feevale.rfu.service.TarefaPendenteService;

@WebService
public class TarefaPendenteWebService extends WebServiceInterface implements TarefaPendenteService {

	@Override
	@WebMethod
	public void save(@WebParam(name="tarefaPendente") TarefasPendente tarefaPendente) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (tarefaPendente.getIdTarefaPendente() == null) {
			query.append("INSERT INTO TarefasPendentes (idItem, status, idTarefa) ");
			query.append("VALUES (?, ?, ?) ");
			
//			parameters.add(tarefaPendente.get);
//			parameters.add(item.getIdPrato());
			
			//TODO
		} else {
			query.append("UPDATE TarefasPendentes SET ");
			query.append(" idItem = ? ");
			query.append(",status = ? ");
			query.append(",idTarefa = ? ");
			query.append(" WHERE idItem = ? ");
			
//			parameters.add(item.getIdPedido());
//			parameters.add(item.getIdPrato());
//			parameters.add(item.getIdItem());
			//TODO
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			tarefaPendente.setIdTarefaPendente(id);
		}
	}

	@Override
	@WebMethod
	public TarefasPendente[] listAll() {
		return null;
	}

}
