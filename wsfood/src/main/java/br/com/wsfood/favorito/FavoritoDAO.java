package br.com.wsfood.favorito;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wsfood.base.BaseDAO;
import br.com.wsfood.prato.Prato;

public class FavoritoDAO extends BaseDAO{
	private EntityManager em = getEntityManager();
	
	public void setFavorito(Favorito favorito){
		em.getTransaction().begin();
		em.persist(favorito);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeFavorito(String id){
		Favorito favorito = new Favorito();
		favorito.setCodigo(Integer.parseInt(id));
		em.getTransaction().begin();
		favorito = em.merge(favorito);
		em.remove(favorito);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Favorito> listarFavoritosUsuario(int codPessoa){
		em.getTransaction().begin();
		Query query = em.createQuery("select f from Favorito f where f.pessoa.codigo = :codigo and f.prato.status = 1", Favorito.class);
		query.setParameter("codigo", codPessoa);
		List<Favorito> favoritos = query.getResultList();
		em.flush();
		em.close();
		return favoritos;
		
	}
	
	public List<Favorito> codigosFavoritosUsuario(int codPessoa){
		String codigos = "";
		Query query = em.createQuery("select f.codigo, f.prato.id from Favorito f "
								   + "where f.pessoa.codigo = :codigo");
		query.setParameter("codigo", codPessoa);
		List<Object[]> results = query.getResultList();
		List<Favorito> favoritos = new ArrayList<Favorito>();
		for(Object[] result : results){
			Favorito f = new Favorito();
			f.setCodigo(Integer.parseInt(result[0].toString()));
			Prato p = new Prato();
			p.setId((Integer.parseInt(result[1].toString())));
			f.setPrato(p);
			favoritos.add(f);
		}
		em.close();
		return favoritos;
	}
}
