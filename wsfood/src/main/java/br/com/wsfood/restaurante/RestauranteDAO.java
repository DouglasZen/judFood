package br.com.wsfood.restaurante;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class RestauranteDAO extends BaseDAO{
	
	private EntityManager em = getEntityManager();
	
	public List<Restaurante> restaurantes(){
		List<Restaurante> r = new ArrayList<Restaurante>();
		Query query = em.createQuery("select r from Restaurante r");
		r = query.getResultList();
		em.close();
		return r;
	}
}
