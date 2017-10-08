package br.com.wsfood.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wsfood.prato.Prato;
import br.com.wsfood.promocao.*;

import br.com.wsfood.promocao.PromocaoDAO;

@Path("/promocao")
public class PromocaoResource {
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPromocoes(){
		List<Promocao> promocoes = new PromocaoDAO().promocoes();
		if(promocoes != null){
			GenericEntity<List<Promocao>> result = new GenericEntity<List<Promocao>>(promocoes){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
