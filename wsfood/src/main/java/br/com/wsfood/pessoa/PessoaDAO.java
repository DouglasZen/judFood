package br.com.wsfood.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class PessoaDAO extends BaseDAO{
	
	private EntityManager em = getEntityManager();
	
	public Pessoa verificaPessoaFacebook(String id){
		Query query = em.createQuery("select p from Pessoa p where p.idFacebook = " + id, Pessoa.class);
		List<Pessoa> list = (List<Pessoa>) query.getResultList();
		if(!list.isEmpty())
			return list.get(0);
		else
			return null;
		
	}
	
	public void setPessoa(Pessoa pessoa){
		em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit();
		
	}
}
