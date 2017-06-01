package br.com.douglas.restaurante.prato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.douglas.restaurante.categoria.Categoria;
import br.com.douglas.restaurante.restaurante.Restaurante;

@Controller
@RequestMapping("prato")
public class PratoController {
	@Autowired
	private PratoService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro() {
		/*Restaurante r = new Restaurante();
		r.setCodigo(1);
		r.setNome("teste restaurante");
		
		Categoria c = new Categoria(1, "categoria 1", r);
		Prato p = new Prato("prato teste", c);
		
		service.addPrato(p);*/
		return "cadastroPrato";
	}
	
	@RequestMapping(value="/cadastro/salvar", 
			method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody Prato prato){
		service.addPrato(prato);
		return "cadastroPrato";
	}	
	
}
