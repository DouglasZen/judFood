package br.com.wsfood.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wsfood.favorito.Favorito;
import br.com.wsfood.favorito.FavoritoDAO;
import br.com.wsfood.prato.Prato;

@Path("/favorito/")
public class FavoritoResource {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Favorito favorito){
		try{
			new FavoritoDAO().setFavorito(favorito);
			return Response.ok().entity(favorito).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("lista/{codPessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listFavoritosPessoa(@PathParam("codPessoa") int codPessoa){
		
		List<Favorito> favoritos = new FavoritoDAO().listarFavoritosUsuario(codPessoa);
		if(favoritos != null){
			GenericEntity<List<Favorito>> result = new GenericEntity<List<Favorito>>(favoritos){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
}
