package br.com.wsfood.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class AvaliacaoDAO extends BaseDAO{
	EntityManager em = getEntityManager();
	
	public List<Avaliacao> ranking(int codCategoria){
		em.getTransaction().begin();
		List<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
		Query query = em.createQuery("select a, sum(a.nota) "
								   + "from Avaliacao a "
								   + "where a.prato.categoria.codigo = :codigo "
								   + "and a.prato.status = 1"
								   + "group by a.prato.id "
								   + "order by sum(a.nota) desc");
		query.setParameter("codigo", codCategoria);
		List<Object[]> results = query.getResultList();
		for(Object[] result : results){
			Avaliacao a = new Avaliacao();
			a = (Avaliacao) result[0];
			a.setMedia(Double.parseDouble(result[1].toString()));
			avaliacao.add(a);
		}
		em.flush();
		em.close();
		return avaliacao;
	}
	
	public Avaliacao setAvaliacao(Avaliacao avaliacao){
		em.getTransaction().begin();
		if(avaliacao.getCodigo() != null){
			em.merge(avaliacao);
			em.flush();
			em.getTransaction().commit();
			
		}else{
			em.persist(avaliacao);
			em.flush();
			em.getTransaction().commit();
			em.refresh(avaliacao);
		}
		
		em.close();
		return avaliacao;
	}
}	
