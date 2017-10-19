package br.com.douglas.restaurante.comentario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.douglas.restaurante.prato.Prato;
import br.com.douglas.restaurante.usuario.Usuario;

@Controller
@RequestMapping("comentario")
public class ComentarioController {
	@Autowired
	private ComentarioService service;
	
	@RequestMapping(value = "/comentariosrestaurante", method = RequestMethod.GET)
	public @ResponseBody List<Comentario> listarComentarioRestaurante(HttpServletRequest request){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Comentario> comentarios = service.listarComentariosRestaurante(usuario.getRestaurante().getCodigo());
		return comentarios;		
	}
	
	@RequestMapping(value="/responder", method = RequestMethod.POST)
	public @ResponseBody boolean responderComentario(@RequestBody RespostaComentario resposta, HttpServletRequest request){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Comentario comentario = new Comentario();
		comentario.setRestaurante(usuario.getRestaurante());
		comentario.setComentario(resposta.getResposta());
		comentario.setPrato(new Prato(resposta.codigo_prato));
		comentario.setCod_comentario(String.valueOf(resposta.getCodigo()));
		service.setResposta(comentario);
		if(comentario.getCodigo() > 0){
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/comentarioprato/{codigoprato}", method = RequestMethod.GET)
	public @ResponseBody List<Comentario> listarComentarioPrato(@PathVariable("codigoprato") int codigo){
		List<Comentario> comentarios = new ArrayList<Comentario>();
		comentarios = service.listarComentarioPrato(codigo);
		return comentarios;
	}
	
	@RequestMapping(value="/getcomentario/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Comentario getcomentario(@PathVariable("codigo") int codigo){
		Comentario comentario = service.getComentario(codigo);
		return comentario;
	}
}
