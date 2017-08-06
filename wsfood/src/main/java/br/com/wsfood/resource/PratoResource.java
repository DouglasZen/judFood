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
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPratos(){
		List<Prato> pratos = new PratoDAO().listarPratos();
		
		if(pratos != null){
			GenericEntity<List<Prato>> result = new GenericEntity<List<Prato>>(pratos){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrato(@PathParam("id") String id){
		Prato prato = null;
		try{
			prato = new PratoDAO().getPrato(Integer.parseInt(id));
			if(prato != null){
				return Response.ok().entity(prato).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
}
