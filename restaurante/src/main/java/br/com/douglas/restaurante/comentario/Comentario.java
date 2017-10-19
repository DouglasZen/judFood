package br.com.douglas.restaurante.comentario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.douglas.restaurante.pessoa.Pessoa;
import br.com.douglas.restaurante.prato.Prato;
import br.com.douglas.restaurante.restaurante.Restaurante;

@Entity
@Table(name = "comentario")
public class Comentario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private int codigo;
	@Column(name="comentario")
	private String comentario;
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name="codigo_restaurante")
	private Restaurante restaurante;
	@ManyToOne
	@JoinColumn(name="codigo_prato")
	private Prato prato;
	@Column(name="codigo_comentario")
	private String cod_comentario;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="codigo_comentario")
	List<Comentario> respostas;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Prato getPrato() {
		return prato;
	}
	public void setPrato(Prato prato) {
		this.prato = prato;
	}
	public String getCod_comentario() {
		return cod_comentario;
	}
	public void setCod_comentario(String cod_comentario) {
		this.cod_comentario = cod_comentario;
	}
	public List<Comentario> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Comentario> respostas) {
		this.respostas = respostas;
	}
	
	
}
