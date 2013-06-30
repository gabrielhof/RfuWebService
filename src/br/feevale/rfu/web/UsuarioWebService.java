package br.feevale.rfu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.feevale.rfu.model.Usuario;
import br.feevale.rfu.model.list.Usuarios;
import br.feevale.rfu.service.UsuarioService;

@Path("Usuario")
@Produces(MediaType.APPLICATION_XML)
public class UsuarioWebService extends WebServiceInterface implements UsuarioService {

	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
	}

	@POST
	@Path("listAll")
	@Override
	public Usuarios listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
