package br.com.douglas.restaurante.prato;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PratoService {
	@Autowired
	private IPrato dao;
	
	public void addPrato(Prato prato){
		dao.addPrato(prato);
	}
	
	public List<Prato> listarPrato(int codigoRestaurante){
		return dao.listarPrato(codigoRestaurante);
	}
	
	public Prato getPrato(int codigo){
		return dao.getPrato(codigo);
	}
	
	public boolean setStatus(int codigo, String status){
		return dao.setStatus(codigo, status);
	}
}
