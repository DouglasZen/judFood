package br.com.wsfood.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class AvaliacaoDAO extends BaseDAO{
	EntityManager em = getEntityManager();
	
	public List<Avaliacao> ranking(int codCategoria){
		List<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
		Query query = em.createQuery("select a, sum(a.nota) "
								   + "from Avaliacao a "
								   + "where a.prato.categoria.codigo = :codigo "
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
		
		return avaliacao;
	}
}	
