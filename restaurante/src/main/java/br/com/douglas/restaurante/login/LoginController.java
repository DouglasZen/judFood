package br.com.douglas.restaurante.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.douglas.restaurante.usuario.Usuario;
import br.com.douglas.restaurante.usuario.UsuarioService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadastroUsuario";
	}
	
	@RequestMapping(value="/logar", 
			method = RequestMethod.POST)
	@ResponseBody
	public boolean validarUsuario(@RequestBody Usuario usuario, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Usuario u = usuarioService.validarLogin(usuario);
		if(u != null){
			request.getSession().setAttribute("usuarioLogado", u);
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/salvar", 
			method = RequestMethod.POST)
	@ResponseBody
	public void setUsuario(@RequestBody Usuario usuario){
		usuarioService.setUsuario(usuario);
	}
}
