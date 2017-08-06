package br.com.wsfood.prato;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.wsfood.base.BaseDAO;


public class PratoDAO extends BaseDAO{
	
	private EntityManager em = getEntityManager();
	
	public List<Prato> listarPratos(){
		Query query = em.createQuery("select p from Prato p", Prato.class);
		List<Prato> pratos = query.getResultList();
		return pratos;
	}
	
	public Prato getPrato(int id){
		Prato prato = (Prato) em.find(Prato.class, id);
		return prato;
	}

	
}
