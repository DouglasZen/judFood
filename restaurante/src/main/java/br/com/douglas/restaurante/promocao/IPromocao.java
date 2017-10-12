package br.com.douglas.restaurante.promocao;

import java.util.List;

public interface IPromocao {
	public List<Promocao> listaPromocoes(int codigo);
	public Promocao savePromocao(Promocao promocao);
	public Promocao getPromocao(int codigo);
}
