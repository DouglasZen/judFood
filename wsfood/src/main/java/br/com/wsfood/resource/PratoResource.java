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
import br.com.wsfood.prato.PratoDAO;

@Path("/prato/")
public class PratoResource {
	
	@GET
	@Path("ranking/{codCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPratos(@PathParam("codCategoria") int codCategoria){
		List<Prato> pratos = new PratoDAO().listarPratos(codCategoria);
		
		if(pratos != null){
			GenericEntity<List<Prato>> result = new GenericEntity<List<Prato>>(pratos){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("restaurante/{codRestaurante}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPratosRestaurante(@PathParam("codRestaurante") int codRestaurante){
		List<Prato> pratos = new PratoDAO().listarPratoRestaurante(codRestaurante);
		if(pratos != null){
			GenericEntity<List<Prato>> result = new GenericEntity<List<Prato>>(pratos){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("{id}/{pessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrato(@PathParam("id") String id, @PathParam("pessoa") String pessoa){
		Prato prato = null;
		try{
			prato = new PratoDAO().getPrato(Integer.parseInt(id), Integer.parseInt(pessoa));
			if(prato != null){
				return Response.ok().entity(prato).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
}
