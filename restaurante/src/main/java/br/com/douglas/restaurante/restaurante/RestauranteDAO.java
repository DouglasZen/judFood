package br.com.douglas.restaurante.restaurante;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RestauranteDAO implements IRestaurante{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	@Override
	public void addRestaurante(Restaurante restaurante) {
		sessionFactory.getCurrentSession().saveOrUpdate(restaurante);
	}
	
	@Override
	public Restaurante getRestaurante(int codigo){
		String q = "select r from Restaurante r where r.codigo = :codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		Restaurante restaurante = (Restaurante) query.getSingleResult();
		return restaurante;
	}
}
