package br.feevale.rfu.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.feevale.rfu.model.Item;
import br.feevale.rfu.service.ItemService;

@WebService
public class ItemWebService extends WebServiceInterface implements ItemService {

	@Override
	@WebMethod
	public void save(@WebParam(name="item") Item item) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		
		if (item.getIdItem() == null) {
			query.append("INSERT INTO Item (idPedido, idPrato) ");
			query.append("VALUES (?, ?) ");
			
			parameters.add(item.getIdPedido());
			parameters.add(item.getIdPrato());
		} else {
			query.append("UPDATE Item SET ");
			query.append(" idPedido = ? ");
			query.append(",idPrato = ? ");
			query.append(" WHERE idItem = ? ");
			
			parameters.add(item.getIdPedido());
			parameters.add(item.getIdPrato());
			parameters.add(item.getIdItem());
		}
		
		Integer id = executeSaveOrUpdate(query.toString(), parameters);
		if (id != null) {
			item.setIdItem(id);
		}
	}
	
	@Override
	@WebMethod
	public Item[] listAll() {
		try {
			PreparedStatement stm = executeQuery("SELECT * FROM Item");
			ResultSet rs = stm.getResultSet();
			
			List<Item> itens = new ArrayList<Item>();
			while (rs.next()) {
				itens.add(createItem(rs));
			}
			
			closeStatement(stm);
			
			return itens.toArray(new Item[itens.size()]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@WebMethod(exclude=true)
	protected Item createItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setIdItem(rs.getInt("idItem"));
		item.setIdPedido(rs.getInt("idPedido"));
		item.setIdPrato(rs.getInt("idPrato"));
		
		return item;
	}
	
}
