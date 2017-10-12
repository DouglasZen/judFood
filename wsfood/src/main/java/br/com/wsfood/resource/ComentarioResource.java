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

import br.com.wsfood.comentario.Comentario;
import br.com.wsfood.comentario.ComentarioDAO;


@Path("/comentario")
public class ComentarioResource {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Comentario comentario){
		try{
			new ComentarioDAO().setComentario(comentario);
			return Response.ok().entity(comentario).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/setResposta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveResposta(Comentario comentario){
		try{
			new ComentarioDAO().setResposta(comentario);
			return Response.ok().entity(comentario).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/lista/{codigoprato}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaComentarios(@PathParam("codigoprato") int codigoPrato, @PathParam("max") int max){
		List<Comentario> comentarios = new ComentarioDAO().listaComentarioPrato(codigoPrato, max);
		if(comentarios != null){
			GenericEntity<List<Comentario>> result = new GenericEntity<List<Comentario>>(comentarios){};
			return Response.ok().entity(result).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/respostas/{codigocomentario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaRespostas(@PathParam("codigocomentario") int codigoComentario){
		Comentario comentarios = new ComentarioDAO().listaRespostaComentario(codigoComentario);
		if(comentarios != null){
			GenericEntity<Comentario> result = new GenericEntity<Comentario>(comentarios){};
			return Response.ok().entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
