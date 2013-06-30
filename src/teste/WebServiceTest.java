package teste;

import br.feevale.rfu.model.Pedido;
import br.feevale.rfu.utils.ServiceUtils;

public class WebServiceTest {

	public static void main(String[] args) throws Exception {
		Pedido pedido = new Pedido();
		pedido.setMesa(1);
		pedido.setTotal(10);
		
		String response = ServiceUtils.request("/Pedido/save", pedido, String.class);
		System.out.println(response);
	}
	
}
