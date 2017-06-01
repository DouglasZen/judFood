package br.com.douglas.restaurante.categoria;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoriaService {
	@Autowired
	private ICategoria dao;
	
	public List<Categoria> listarCategoria(int codigoRestaurante){
		return dao.listCategoria(codigoRestaurante);
	}
}
