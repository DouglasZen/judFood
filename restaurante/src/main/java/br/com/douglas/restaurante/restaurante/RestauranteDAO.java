package br.com.douglas.restaurante.restaurante;

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

}
