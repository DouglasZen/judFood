package br.com.douglas.restaurante.usuario;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO implements IUsuario{
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public Usuario setUsuario(Usuario usuario) {
		session.getCurrentSession().saveOrUpdate(usuario);
		return usuario;
		
	}

	@Override
	public Usuario validarLogin(Usuario usuario) {
		String q = "select u from Usuario u where u.email = :email and u.senha =:senha";
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		List<Usuario> result = query.getResultList();
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	@Override
	public Usuario getUsuario(int codigo) {
		String q = "select u from Usuario u where u.codigo = :codigo";
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario;
	}

	@Override
	public List<Usuario> listaUsuario(int codigo) {
		String q = "select u from Usuario u where u.restaurante.codigo = :codigo";
		Query query = session.getCurrentSession().createQuery(q);
		query.setParameter("codigo", codigo);
		List<Usuario> usuario = query.getResultList();
		
		return usuario;
	}
	
	
}
