package br.com.wsfood.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class CategoriaDAO extends BaseDAO{
	private EntityManager em = getEntityManager();
	
	public List<Categoria> listarCategoria(){
		Query query = (Query) em.createQuery("select c from Categoria c", Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
}
