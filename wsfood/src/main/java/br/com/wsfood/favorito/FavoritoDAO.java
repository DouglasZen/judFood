package br.com.wsfood.favorito;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;

public class FavoritoDAO extends BaseDAO{
	private EntityManager em = getEntityManager();
	
	public void setFavorito(Favorito favorito){
		em.getTransaction().begin();
		em.persist(favorito);
		em.getTransaction().commit();
	}
	
	public List<Favorito> listarFavoritosUsuario(int codPessoa){
		Query query = em.createQuery("select f from Favorito f where f.pessoa.codigo = :codigo", Favorito.class);
		query.setParameter("codigo", codPessoa);
		List<Favorito> favoritos = query.getResultList();
		return favoritos;
	}
}
