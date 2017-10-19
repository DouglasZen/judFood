package br.com.douglas.restaurante.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/editar/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") int codigo, Model model){
		model.addAttribute("codigo", codigo);
		return new ModelAndView("cadastroUsuario");
	}
	
	@RequestMapping(value = "/listausuarios/{codigo}")
	public @ResponseBody List<Usuario> listaUsuarios(@PathVariable("codigo") int codigo){
		return service.listaUsuario(codigo);
	}
	
	@RequestMapping(value = "/getUsuario/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Usuario getUsuario(@PathVariable("codigo") int codigo){
		return service.getUsuario(codigo);
	}
}
