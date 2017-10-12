package br.com.wsfood.comentario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.wsfood.base.BaseDAO;

public class ComentarioDAO extends BaseDAO{
	
	EntityManager em = getEntityManager();
	
	public void setComentario(Comentario comentario){
		em.getTransaction().begin();
		em.merge(comentario);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void setResposta(Comentario comentario){
		em.getTransaction().begin();
		em.merge(comentario);
		em.flush();
		em.getTransaction().commit();
	}
	
	public List<Comentario> listaComentarioPrato(int codigoPrato, int max){
		Query query = em.createQuery("select c"
				                   + " from Comentario c"
				                   + " where c.prato.id = :codigo"
				                   + " and c.codComentario IS NULL"
								   + " order by c.codigo desc", Comentario.class);
		query.setParameter("codigo", codigoPrato);
		if(max > 0){
			query.setMaxResults(max);
		}
		List<Comentario> comentarios = query.getResultList();
		return comentarios;
	}
	
	public Comentario listaRespostaComentario(int codigoComentario){
		Query query = em.createQuery("select c"
								   + " from Comentario c"
								   + " where c.codigo = :codigo", Comentario.class);
		query.setParameter("codigo", codigoComentario);
		Comentario comentarios = (Comentario) query.getResultList().get(0);
		return comentarios;
	}
}
