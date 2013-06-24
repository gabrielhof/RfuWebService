package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.feevale.rfu.model.Pedido;
import br.feevale.rfu.service.PedidoService;

@WebService
public class PedidoWebService extends WebServiceInterface implements PedidoService {

	@Override
	@WebMethod
	public void save(@WebParam(name="pedido") Pedido pedido) {
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

	@Override
	@WebMethod
	public Pedido[] listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Pedido");
			ResultSet rs = stm.getResultSet();
			
			List<Pedido> pedidos = new ArrayList<Pedido>();
			while (rs.next()) {
				pedidos.add(createPedido(rs));
			}
			
			closeStatement(stm);
			
			return pedidos.toArray(new Pedido[pedidos.size()]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@WebMethod(exclude=true)
	public Pedido createPedido(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(rs.getInt("idPedido"));
		pedido.setTotal(rs.getInt("total"));
		pedido.setMesa(rs.getInt("mesa"));
		
		return pedido;
	}

}
