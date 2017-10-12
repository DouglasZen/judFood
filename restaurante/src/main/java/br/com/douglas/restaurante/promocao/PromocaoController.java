package br.com.douglas.restaurante.promocao;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.douglas.restaurante.prato.Prato;
import br.com.douglas.restaurante.usuario.Usuario;

@Controller
@RequestMapping("promocao")
public class PromocaoController {
	
	@Autowired
	private PromocaoService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadastroPromocao";
	}
	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
	public String consulta(){
		return "consultaPromocao";
	}
	
	@RequestMapping(value = "/editar/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") int codigo, Model model){
		model.addAttribute("nome", codigo);
		return new ModelAndView("cadastroPromocao");
	}
	
	@RequestMapping(value = "/listarpromocao", method = RequestMethod.GET)
	public @ResponseBody List<Promocao> listarPromocao(HttpServletRequest request, HttpServletResponse response){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Promocao> promocoes = service.listPromocao(usuario.getRestaurante().getCodigo());
		return promocoes;
	}
	
	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	@ResponseBody
	public Promocao savePromocao(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		Promocao promocao = new Promocao();
		promocao = new Gson().fromJson(request.getParameter("promocao"), Promocao.class);
		if(promocao.getCodigo() != null){
			MultipartFile file = request.getFile("imagem");
			if(file != null){
				byte[] imagem = file.getBytes();
				String img64 = Base64.getEncoder().encodeToString(imagem);
				promocao.setImagem(img64);
			}
			service.savePromocao(promocao);
		}else{
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			MultipartFile file = request.getFile("imagem");
			byte[] imagem = file.getBytes();
			String img64 = Base64.getEncoder().encodeToString(imagem);
			promocao.setImagem(img64);
			promocao.setRestaurante(usuario.getRestaurante());
			service.savePromocao(promocao);
		}
		return promocao;
	}
	
	@RequestMapping(value = "/getPromocao/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Promocao promocao(@PathVariable("codigo") int codigo){
		return service.getPromocao(codigo);
	}
	
}
