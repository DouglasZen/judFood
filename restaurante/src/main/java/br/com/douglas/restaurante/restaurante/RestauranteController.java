package br.com.douglas.restaurante.restaurante;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.douglas.restaurante.usuario.Usuario;
import br.com.douglas.restaurante.usuario.UsuarioService;

@Controller
@RequestMapping("restaurante")
public class RestauranteController {
	
	@Autowired
	private RestauranteService rservice;
	@Autowired
	private UsuarioService uservice;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadRestaurante";
	}
	
	@RequestMapping(value="editar", method = RequestMethod.GET)
	public ModelAndView editar(Model model, HttpServletRequest request, HttpServletResponse response){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado"); 
		model.addAttribute("codigo", usuario.getRestaurante().getCodigo());
		return new ModelAndView("cadRestaurante");
	}
	
	@RequestMapping(value="/cadastro/salvar", 
					method = RequestMethod.POST)
	@ResponseBody
	public boolean save(@RequestBody Restaurante restaurante, HttpServletRequest request, HttpServletResponse response){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		rservice.addRestaurante(restaurante);
		usuario.setRestaurante(restaurante);
		uservice.setUsuario(usuario);
		request.getSession().setAttribute("usuarioLogado", usuario);
		
		if(restaurante.getCodigo() > 0){
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/getRestaurante/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Restaurante restaurante(@PathVariable("codigo") int codigo){
		return rservice.getRestaurante(codigo);
	}
	
	
	
}
