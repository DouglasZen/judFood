package br.com.douglas.restaurante.comentario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ComentarioService {
	@Autowired
	private IComentario dao;
	
	public List<Comentario> listarComentariosRestaurante(int codigoRestaurante){
		return dao.ultimosComentarioRestaurante(codigoRestaurante);
	}
	
	public Comentario setResposta(Comentario comentario){
		return dao.setComentario(comentario);
	}
	
	public List<Comentario> listarComentarioPrato(int codigoPrato){
		return dao.ultimosComentarioPrato(codigoPrato);
	}
	
	public Comentario getComentario(int codigo){
		return dao.getComentario(codigo);
	}
}
