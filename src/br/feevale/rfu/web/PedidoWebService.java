package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.feevale.rfu.model.Pedido;
import br.feevale.rfu.model.list.Pedidos;
import br.feevale.rfu.service.PedidoService;

@Path("/Pedido")
public class PedidoWebService extends WebServiceInterface implements PedidoService {

	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public void save(Pedido pedido) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (pedido.getIdPedido() == null) {
			query.append("INSERT INTO Pedido (total, mesa) ");
			query.append("VALUES (?, ?) ");
			
			parameters.add(pedido.getTotal());
			parameters.add(pedido.getMesa());
		} else {
			query.append("UPDATE Pedido SET ");
			query.append(" total = ? ");
			query.append(",mesa = ? ");
			query.append(" WHERE idPedido = ? ");
			
			parameters.add(pedido.getTotal());
			parameters.add(pedido.getMesa());
			parameters.add(pedido.getIdPedido());
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			pedido.setIdPedido(id);
		}
	}

	@POST
	@Path("listAll")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public Pedidos listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Pedido");
			ResultSet rs = stm.getResultSet();
			
			Pedidos pedidos = new Pedidos();
			while (rs.next()) {
				pedidos.add(createPedido(rs));
			}
			
			closeStatement(stm);
			
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Pedido createPedido(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(rs.getInt("idPedido"));
		pedido.setTotal(rs.getInt("total"));
		pedido.setMesa(rs.getInt("mesa"));
		
		return pedido;
	}

}
