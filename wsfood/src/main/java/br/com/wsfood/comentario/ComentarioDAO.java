package br.com.wsfood.comentario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.wsfood.base.BaseDAO;
import br.com.wsfood.pessoa.Pessoa;

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
		em.getTransaction().begin();
		Query query = em.createQuery("select c.codigo, "
								   + "c.comentario, "
								   + "c.pessoa ,"
								   + " (select count(r.codigo) from Comentario r where r.codComentario = c.codigo)"
				                   + " from Comentario c"
				                   + " where c.prato.id = :codigo"
				                   + " and c.codComentario IS NULL"
								   + " order by c.codigo desc");
		query.setParameter("codigo", codigoPrato);
		if(max > 0){
			query.setMaxResults(max);
		}
		List<Comentario> comentarios = new ArrayList<Comentario>();
		List<Object[]> results = query.getResultList();
		for(Object[] result : results){
			Comentario comentario = new Comentario();
			comentario.setCodigo(Integer.parseInt(result[0].toString()));
			comentario.setComentario(result[1].toString());
			Pessoa pessoa = new Pessoa();
			pessoa = (Pessoa) result[2];
			comentario.setPessoa(pessoa);
			comentario.setTotal(Integer.parseInt(result[3].toString()));
			comentarios.add(comentario);
		}
		em.flush();
		em.close();
		return comentarios;
	}
	
	public Comentario getComentario(int codigoComentario){
		Query query = em.createQuery("select c"
				   				   + " from Comentario c"
				                   + " where c.codigo = :codigo");
		query.setParameter("codigo", codigoComentario);
		Comentario comentarios = (Comentario) query.getSingleResult();
		return comentarios;
	}
	
	public Comentario listaRespostaComentario(int codigoComentario){
		Query query = em.createQuery("select c"
								   + " from Comentario c"
								   + " where c.codigo = :codigo");
		query.setParameter("codigo", codigoComentario);
		Comentario comentarios = (Comentario) query.getSingleResult();
		return comentarios;
	}
}
