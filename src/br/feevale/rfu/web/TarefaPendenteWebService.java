package br.feevale.rfu.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.feevale.rfu.model.TarefasPendente;
import br.feevale.rfu.model.list.TarefasPendentes;
import br.feevale.rfu.service.TarefaPendenteService;

@Path("/TarefaPendente")
@Produces(MediaType.APPLICATION_XML)
public class TarefaPendenteWebService extends WebServiceInterface implements TarefaPendenteService {

	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public void save(TarefasPendente tarefaPendente) {
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

	@POST
	@Path("listAll")
	@Override
	public TarefasPendentes listAll() {
		return null;
	}

}
