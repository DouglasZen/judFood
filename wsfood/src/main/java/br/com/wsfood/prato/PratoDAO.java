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

import br.com.wsfood.avaliacao.Avaliacao;
import br.com.wsfood.base.BaseDAO;


public class PratoDAO extends BaseDAO{
	
	private EntityManager em = getEntityManager();
	
	public List<Prato> listarPratos(int codCategoria){
		Query query = em.createQuery("select p from Prato p where p.categoria.codigo = :codigo", Prato.class);
		query.setParameter("codigo", codCategoria);
		List<Prato> pratos = query.getResultList();
		return pratos;
	}
	
	public List<Prato> listarPratoRestaurante(int codigoRestaurante){
		Query query = em.createQuery("select p from Prato p where p.restaurante.codigo = :codigo", Prato.class);
		query.setParameter("codigo", codigoRestaurante);
		List<Prato> pratos = query.getResultList();
		return pratos;
	}
	
	/*public Prato getPrato(int id){
		Prato prato = (Prato) em.find(Prato.class, id);
		return prato;
	}*/
	
	public Prato getPrato(int codigo, int codigo_pessoa){
		Prato prato = new Prato();
		Query query = em.createQuery("select p,"
								   + "(select a "
								   + " from Avaliacao a "
								   + " where a.prato.id = p.id "
								   + " and a.pessoa.codigo = :codigo_pessoa),"
								   + " (select count(c.codigo)"
								   + "  from Comentario c"
								   + "  where c.prato.id = :codigo)"
								   + "from Prato p "
								   + "where p.id = :codigo");
		query.setParameter("codigo_pessoa", codigo_pessoa);
		query.setParameter("codigo", codigo);
		List<Object[]> results = query.getResultList();
		for(Object[] result : results){
			prato = (Prato) result[0];
			if(result[1] != null){
				Avaliacao a = new Avaliacao();
				a = (Avaliacao) result[1];
				prato.setMedia(a.getNota());
				prato.setCod_avaliacao(a.getCodigo());
			}
			prato.setTotal_comentario(Integer.parseInt(result[2].toString()));
		}
		
		return prato;
	}

	
}
