package br.com.douglas.restaurante.prato;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import br.com.douglas.restaurante.categoria.Categoria;
import br.com.douglas.restaurante.restaurante.Restaurante;

@Controller
@RequestMapping("prato")
public class PratoController {
	@Autowired
	private PratoService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro() {
		return "cadastroPrato";
	}
	
	
	@RequestMapping(value = "/cadastro/salvar",
			method = RequestMethod.POST)
	@ResponseBody
	public void salvar(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		Prato p = new Prato();
		p = new Gson().fromJson(request.getParameter("prato"), Prato.class);
		MultipartFile file = request.getFile("imagem");
		
		byte[] imagem = file.getBytes();
		String img64 = Base64.getEncoder().encodeToString(imagem);
		p.setImagem(img64);
		service.addPrato(p);
		
	
	}
	
}
