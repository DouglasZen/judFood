package br.com.douglas.restaurante.prato;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PratoDAO implements IPrato{
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Prato addPrato(Prato prato) {
		sessionFactory.getCurrentSession().saveOrUpdate(prato);
		return prato;
	}

	@Override
	public List<Prato> listarPrato(int codigoRestaurante) {
		String q = "select p from Prato p where p.restaurante.codigo = :codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigoRestaurante);	
		List<Prato> pratos = query.getResultList();
		return pratos;
	}

	@Override
	public Prato getPrato(int codigo) {
		String q = "select p from Prato p where p.id = :codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		Prato prato = (Prato) query.getSingleResult();
		return prato;
	}

	@Override
	public double getMedia(int codigo) {
		
		return 0;
	}
	
	
	
}
