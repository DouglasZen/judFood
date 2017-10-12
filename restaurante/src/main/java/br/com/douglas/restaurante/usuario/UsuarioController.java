package br.com.douglas.restaurante.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value = "/listausuarios/{codigo}")
	public @ResponseBody List<Usuario> listaUsuarios(@PathVariable("codigo") int codigo){
		return service.listaUsuario(codigo);
	}
}
