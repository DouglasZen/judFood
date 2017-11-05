package br.com.douglas.restaurante.prato;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.douglas.restaurante.categoria.Categoria;
import br.com.douglas.restaurante.restaurante.Restaurante;
import br.com.douglas.restaurante.usuario.Usuario;

@Controller
@RequestMapping("prato")
public class PratoController {
	@Autowired
	private PratoService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro() {
		return "cadastroPrato";
	}
	
	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
	public String consulta(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Prato> pratos = service.listarPrato(usuario.getRestaurante().getCodigo());
		request.setAttribute("pratos", pratos);
		return "consultaPrato";
	}
	
	@RequestMapping(value = "/editar/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") int codigo, Model model){
		model.addAttribute("codigo" , codigo);
		return new ModelAndView("cadastroPrato");
	}
	
	@RequestMapping(value = "/cadastro/salvar",
			method = RequestMethod.POST)
	@ResponseBody
	public Prato salvar(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		Prato p = new Prato();
		p = new Gson().fromJson(request.getParameter("prato"), Prato.class);
		if(p.getId() != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			MultipartFile file = request.getFile("imagem");
			if(file != null){
				byte[] imagem = file.getBytes();
				String img64 = Base64.getEncoder().encodeToString(imagem);
				p.setImagem(img64);
			}
			p.setRestaurante(usuario.getRestaurante());
			service.addPrato(p);
		}else{
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			MultipartFile file = request.getFile("imagem");
			byte[] imagem = file.getBytes();
			String img64 = Base64.getEncoder().encodeToString(imagem);
			p.setImagem(img64);
			p.setRestaurante(usuario.getRestaurante());
			p.setStatus("1");
			service.addPrato(p);
		}
		
		return p;
	}
	
	@RequestMapping(value = "/listarpratos", method = RequestMethod.GET)
	public @ResponseBody List<Prato> listarPratos(HttpServletRequest request, HttpServletResponse response){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Prato> pratos = service.listarPrato(usuario.getRestaurante().getCodigo());
		
		return pratos;
	}
	
	@RequestMapping(value = "/getPrato/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Prato prato(@PathVariable("codigo") int codigo){
		return service.getPrato(codigo);
	}
	
	@RequestMapping(value="/setStatus/{codigo}/{status}", method = RequestMethod.GET)
	public @ResponseBody boolean setStatus(@PathVariable("codigo") int codigo, @PathVariable("status") String status){
		return service.setStatus(codigo, status);
	}
	
}
