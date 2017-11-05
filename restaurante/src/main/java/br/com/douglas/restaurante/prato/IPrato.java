package br.com.douglas.restaurante.prato;

import java.util.List;

public interface IPrato {
	public Prato addPrato(Prato prato);
	public List<Prato> listarPrato(int codigoRestaurante);
	public Prato getPrato(int codigo);
	public double getMedia(int codigo);
	public boolean setStatus(int codigo, String status);
}
