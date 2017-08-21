package br.com.wsfood.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wsfood.categoria.Categoria;
import br.com.wsfood.categoria.CategoriaDAO;
import br.com.wsfood.prato.Prato;

@Path("/categoria/")
public class CategoriaResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorias(){
		List<Categoria> categorias = new CategoriaDAO().listarCategoria();
		if(categorias != null){
			GenericEntity<List<Categoria>> result = new GenericEntity<List<Categoria>>(categorias){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
