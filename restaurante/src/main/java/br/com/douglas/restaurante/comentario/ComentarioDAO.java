package br.com.douglas.restaurante.comentario;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioDAO implements IComentario{
	
	@Autowired
    private SessionFactory session;
	
	@Override
	public Comentario getComentario(int codigoComentario) {
		String q = "select c from Comentario c "
				+ " where c.codigo = :codigo";
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigoComentario);
		Comentario comentario = (Comentario) query.getSingleResult();
		return comentario;
	}

	@Override
	public Comentario setComentario(Comentario comentario) {
		session.getCurrentSession().saveOrUpdate(comentario);
		return comentario;
	}



	@Override
	public List<Comentario> ultimosComentarioRestaurante(int codigoRestaurante) {
		String q = "select c from Comentario c "
				+ " where c.restaurante.codigo = :codigo "
				+ " and c.codigo not in (select r.cod_comentario from Comentario r where r.cod_comentario != NULL)"
				+ " and c.cod_comentario = NULL"
				+ " order by c.codigo desc";
				
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigoRestaurante);
		query.setMaxResults(6);
		List<Comentario> comentarios = query.getResultList();
		return comentarios;
	}



	@Override
	public List<Comentario> ultimosComentarioPrato(int codigoPrato) {
		String q = "select c from Comentario c"
				+ " where c.prato.id = :codigo"
				+ " and c.cod_comentario IS NULL"
				+ " order by c.codigo desc";
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigoPrato);
		query.setMaxResults(5);
		List<Comentario> comentarios = query.getResultList();
		return comentarios;
	}

}
