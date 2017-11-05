package br.com.douglas.restaurante.promocao;

import java.text.SimpleDateFormat;
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
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private PromocaoService service;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(){
		return "cadastroPromocao";
	}
	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
	public String consulta(HttpServletRequest request, HttpServletResponse response){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Promocao> promocoes = service.listPromocao(usuario.getRestaurante().getCodigo());
		request.setAttribute("promocoes", promocoes);
		return "consultaPromocao";
	}
	
	@RequestMapping(value = "/editar/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") int codigo, Model model){
		model.addAttribute("codigo", codigo);
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
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		promocao = new Gson().fromJson(request.getParameter("promocao"), Promocao.class);
		promocao.setData_inicio(formato.parse(promocao.getData_inicio_str()));
		promocao.setData_fim(formato.parse(promocao.getData_fim_str()));
		if(promocao.getCodigo() != null){
			MultipartFile file = request.getFile("imagem");
			if(file != null){
				byte[] imagem = file.getBytes();
				String img64 = Base64.getEncoder().encodeToString(imagem);
				promocao.setImagem(img64);
			}
			promocao.setRestaurante(usuario.getRestaurante());
			service.savePromocao(promocao);
		}else{
			MultipartFile file = request.getFile("imagem");
			byte[] imagem = file.getBytes();
			String img64 = Base64.getEncoder().encodeToString(imagem);
			promocao.setImagem(img64);
			promocao.setRestaurante(usuario.getRestaurante());
			promocao.setStatus("1");
			service.savePromocao(promocao);
		}
		return promocao;
	}
	
	@RequestMapping(value = "/getPromocao/{codigo}", method = RequestMethod.GET)
	public @ResponseBody Promocao promocao(@PathVariable("codigo") int codigo){
		Promocao promocao = new Promocao();
		promocao = service.getPromocao(codigo);
		promocao.setData_inicio_str(formato.format(promocao.getData_inicio()));
		promocao.setData_fim_str(formato.format(promocao.getData_fim()));
		promocao.setData_inicio(null);
		promocao.setData_fim(null);
		return promocao;
	}
	
	@RequestMapping(value="/setStatus/{codigo}/{status}", method=RequestMethod.GET)
	public @ResponseBody boolean setStatus(@PathVariable("codigo") int codigo, @PathVariable("status") String status){
		return service.setStatus(codigo, status);
	}
	
}
