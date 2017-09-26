package br.com.douglas.restaurante.categoria;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.douglas.restaurante.prato.Prato;
import br.com.douglas.restaurante.restaurante.Restaurante;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadastroCategoria";
	}
	
	public ModelAndView listaCategoria(){
		List<Categoria> categorias = categorias();
		ModelAndView mav = new ModelAndView();
		mav.addObject("categorias", categorias);
		
		return mav;
	}
	
	@RequestMapping(value = "/listCategoria", method = RequestMethod.GET)
	public @ResponseBody List<Categoria> categorias(){
		int codigoRestaurante = 1;
		List<Categoria> categoria = service.listarCategoria(codigoRestaurante); 
		return categoria;
	}
	
	@RequestMapping(value = "/cadastro/salvar",
			method = RequestMethod.POST)
	@ResponseBody
	public void salvar(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		Categoria categoria = new Categoria();
		categoria = new Gson().fromJson(request.getParameter("categoria"), Categoria.class);
		//------------
		Restaurante r = new Restaurante();
		r.setCodigo(1);
		categoria.setRestaurante(r);
		//----------
		MultipartFile file = request.getFile("imagem");
		
		byte[] imagem = file.getBytes();
		String img64 = Base64.getEncoder().encodeToString(imagem);
		categoria.setImagem(img64);
		service.salvarCategoria(categoria);
	}
}
