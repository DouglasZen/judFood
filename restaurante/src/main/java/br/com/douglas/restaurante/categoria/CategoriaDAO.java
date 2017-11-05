package br.com.douglas.restaurante.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.douglas.restaurante.restaurante.Restaurante;
@Repository
public class CategoriaDAO implements ICategoria{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<Categoria> listCategoria(int codigoRestaurante) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Categoria.class);
		//Restaurante r = new Restaurante();
		//r.setCodigo(codigoRestaurante);
		//crit.add(Restrictions.eq("restaurante", r));
		return crit.list();
		
	}

	@Override
	public void salvarCategoria(Categoria categoria) {
		sessionFactory.getCurrentSession().saveOrUpdate(categoria);
		
	}

}
