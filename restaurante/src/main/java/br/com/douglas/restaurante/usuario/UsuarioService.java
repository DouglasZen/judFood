package br.com.douglas.restaurante.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private IUsuario dao;
	
	public Usuario validarLogin(Usuario usuario){
		return dao.validarLogin(usuario);
	}
	
	public Usuario setUsuario(Usuario usuario){
		dao.setUsuario(usuario);
		return usuario;
	}
	
	public List<Usuario> listaUsuario(int codigo){
		return dao.listaUsuario(codigo);
	}
}
