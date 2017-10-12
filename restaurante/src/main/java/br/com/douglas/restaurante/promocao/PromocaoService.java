package br.com.douglas.restaurante.promocao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PromocaoService {
	@Autowired
	private IPromocao dao;
	
	public List<Promocao> listPromocao(int codigo){
		return dao.listaPromocoes(codigo);
	}
	
	public Promocao savePromocao(Promocao promocao){
		return dao.savePromocao(promocao);
	}
	
	public Promocao getPromocao(int codigo){
		return dao.getPromocao(codigo);
	}
}
