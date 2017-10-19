package br.com.douglas.restaurante.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.douglas.restaurante.usuario.Usuario;

@Controller
@RequestMapping("home")
public class HomeController {
	@RequestMapping(value = "/")
	public ModelAndView home(Model model, HttpServletRequest request){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		if(usuario.getRestaurante() != null){
			model.addAttribute("rest", "ok");
			return new ModelAndView("home");
		}else{
			model.addAttribute("rest", "");
			return new ModelAndView("home");
		}
	}
	/*public String home(){
		return "home";
	}*/
}
