package br.com.douglas.restaurante.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService dao;
	
	
	public ModelAndView listaCategoria(){
		List<Categoria> categorias = categorias();
		ModelAndView mav = new ModelAndView();
		mav.addObject("categorias", categorias);
		
		return mav;
	}
	
	@RequestMapping(value = "/listCategoria", method = RequestMethod.GET)
	public @ResponseBody List<Categoria> categorias(){
		int codigoRestaurante = 1;
		List<Categoria> categoria = dao.listarCategoria(codigoRestaurante); 
		return categoria;
	}
}
