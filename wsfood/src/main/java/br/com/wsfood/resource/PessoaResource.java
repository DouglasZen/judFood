package br.com.wsfood.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wsfood.pessoa.Pessoa;
import br.com.wsfood.pessoa.PessoaDAO;
import br.com.wsfood.util.Resultado;

@Path("/pessoa/")
public class PessoaResource {
	
	@GET
	@Path("facebook/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPessoaFacebook(@PathParam("id") String id){
		Pessoa pessoa = new Pessoa(); 
		pessoa = new PessoaDAO().verificaPessoaFacebook(id);
		if(pessoa != null){
			return Response.ok().entity(pessoa).build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Pessoa pessoa){
		try{
			new PessoaDAO().setPessoa(pessoa);
			return Response.ok().entity(pessoa).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLogin(Pessoa pessoa){
		Resultado resultado = new PessoaDAO().login(pessoa.getEmail(), pessoa.getSenha());
		if(resultado.isResultado()){
			return Response.ok().entity(resultado).build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
}
