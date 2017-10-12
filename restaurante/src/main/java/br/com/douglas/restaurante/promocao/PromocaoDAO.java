package br.com.douglas.restaurante.promocao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PromocaoDAO implements IPromocao{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<Promocao> listaPromocoes(int codigo) {
		String q = "select p from Promocao p where p.restaurante.codigo = :codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		List<Promocao> promocoes = query.getResultList();
		return promocoes;
	}
	
	public Promocao savePromocao(Promocao promocao){
		sessionFactory.getCurrentSession().saveOrUpdate(promocao);
		return promocao;
	}

	@Override
	public Promocao getPromocao(int codigo) {
		String q = "select p from Promocao p where p.codigo = :codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		Promocao promocao = (Promocao) query.getSingleResult();
		return promocao;
	}
	
	

}
