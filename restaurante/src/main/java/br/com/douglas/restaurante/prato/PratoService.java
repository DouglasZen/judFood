package br.com.douglas.restaurante.prato;

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
	
	
}
