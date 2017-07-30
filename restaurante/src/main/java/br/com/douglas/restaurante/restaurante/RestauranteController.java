package br.com.douglas.restaurante.restaurante;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("restaurante")
public class RestauranteController {
	
	@Autowired
	private RestauranteService rservice;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadRestaurante";
	}
	
	@RequestMapping(value="/cadastro/salvar", 
					method = RequestMethod.POST)
	@ResponseBody
	public Restaurante save(@RequestBody Restaurante restaurante){
		rservice.addRestaurante(restaurante);
		return restaurante;
	}
	
	
	
}
