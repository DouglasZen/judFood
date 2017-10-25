package br.com.wsfood.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;
import br.com.wsfood.util.Resultado;

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
	
	public Pessoa login(String email, String senha){
		
		Query query = em.createQuery("select p from Pessoa p where p.email = :email and p.senha = :senha" , Pessoa.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		if(!query.getResultList().isEmpty()){
			Pessoa pessoa = (Pessoa) query.getSingleResult();
			return pessoa;
		}else{
			return new Pessoa();
		}
		
	}
	
	public boolean verificarEmail(String email){
		Query query = em.createQuery("select p from Pessoa p where p.email = :email");
		query.setParameter("email", email);
		if(!query.getResultList().isEmpty()){
			return true;
		}
		return false;
	}
	
	public void setPessoa(Pessoa pessoa){
		em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit();
		
	}
}
