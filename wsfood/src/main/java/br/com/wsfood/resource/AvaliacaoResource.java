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

import br.com.wsfood.avaliacao.Avaliacao;
import br.com.wsfood.avaliacao.AvaliacaoDAO;
import br.com.wsfood.promocao.Promocao;

@Path("/avaliacao")
public class AvaliacaoResource {
	@GET
	@Path("/ranking/{codcategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ranking(@PathParam("codcategoria") int codcategoria){
		List<Avaliacao> ranking = new AvaliacaoDAO().ranking(codcategoria);
		if(ranking != null){
			GenericEntity<List<Avaliacao>> result = new GenericEntity<List<Avaliacao>>(ranking){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
