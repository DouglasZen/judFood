package br.com.wsfood.categoria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class CategoriaDAO extends BaseDAO{
	private EntityManager em = getEntityManager();
	
	public List<Categoria> listarCategoria(){
		String q = "select c.codigo, c.descricao"
				+ " from Categoria c";
				
		Query query = (Query) em.createQuery(q);
		List<Categoria> categorias = new ArrayList<Categoria>();
		List<Object[]> results = query.getResultList();
		for(Object[] result : results){
			Categoria categoria = new Categoria();
			categoria.setCodigo(Integer.parseInt(result[0].toString()));
			categoria.setDescricao(result[1].toString());
			categorias.add(categoria);
		}
		em.close();
		return categorias;
	}
}
