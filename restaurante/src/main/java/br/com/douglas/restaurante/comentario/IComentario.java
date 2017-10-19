package br.com.douglas.restaurante.comentario;

import java.util.List;

public interface IComentario {
	public Comentario getComentario(int codigoComentario);
	public List<Comentario> ultimosComentarioRestaurante(int codigoRestaurante);
	public List<Comentario> ultimosComentarioPrato(int codigoPrato);
	public Comentario setComentario(Comentario comentario);
}
