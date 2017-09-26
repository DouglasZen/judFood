package br.com.wsfood.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wsfood.prato.Prato;
import br.com.wsfood.restaurante.Restaurante;
import br.com.wsfood.restaurante.RestauranteDAO;

@Path("/restaurante")
public class RestauranteResource {
	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listaRestaurante(){
		List<Restaurante> restaurantes = new RestauranteDAO().restaurantes();
		if(restaurantes != null){
			GenericEntity<List<Restaurante>> result = new GenericEntity<List<Restaurante>>(restaurantes){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
