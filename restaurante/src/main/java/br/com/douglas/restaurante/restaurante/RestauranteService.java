package br.com.douglas.restaurante.restaurante;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class RestauranteService {
	 @Autowired
	 private IRestaurante dao;
	 
	 public void addRestaurante(Restaurante restaurante){
		 dao.addRestaurante(restaurante);
		 
	 }
	 
	 
}
