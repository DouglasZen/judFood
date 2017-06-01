package br.com.douglas.restaurante.prato;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PratoDAO implements IPrato{
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public void addPrato(Prato prato) {
		sessionFactory.getCurrentSession().saveOrUpdate(prato);
		
	}
	
	
}
