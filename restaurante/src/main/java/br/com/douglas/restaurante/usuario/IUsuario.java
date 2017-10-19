package br.com.douglas.restaurante.usuario;

import java.util.List;

public interface IUsuario {
	public Usuario setUsuario(Usuario usuario);
	public Usuario validarLogin(Usuario usuario);
	public Usuario getUsuario(int codigo);
	public List<Usuario> listaUsuario(int codigo);
}
