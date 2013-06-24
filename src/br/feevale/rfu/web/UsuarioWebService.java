package br.feevale.rfu.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.feevale.rfu.model.Usuario;
import br.feevale.rfu.service.UsuarioService;

@WebService
public class UsuarioWebService extends WebServiceInterface implements UsuarioService {

	@Override
	@WebMethod
	public void save(@WebParam(name="usuario") Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@WebMethod
	public Usuario[] listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
