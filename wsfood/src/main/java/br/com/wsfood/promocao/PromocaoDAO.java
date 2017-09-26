package br.com.wsfood.promocao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.wsfood.base.BaseDAO;

public class PromocaoDAO extends BaseDAO{
	
	private EntityManager em = getEntityManager();
	
	public List<Promocao> promocoes(int codigo_restaurante){
		Date data = new Date();
		Query query = em.createQuery("select p from Promocao p where p.dataini <= :data1 and p.datafim > :data2");
		query.setParameter("data1", data, TemporalType.TIMESTAMP);
		query.setParameter("data2", data, TemporalType.TIMESTAMP);
		List<Promocao> p = query.getResultList();
		
		return p;
	}
}
