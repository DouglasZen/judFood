package br.com.wsfood.prato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.wsfood.categoria.Categoria;
import br.com.wsfood.restaurante.Restaurante;



@Entity
@Table(name = "prato")
@XmlRootElement
public class Prato {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer id;
	@Column(name="descricao")
	private String descricao;
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;
	@Column(name="imagem")
	private String imagem;	
	@Column(name="nome")
	private String nome;
	@ManyToOne
	@JoinColumn(name="codigo_restaurante")
	private Restaurante restaurante;
	
	@Transient
	private double media;
	@Transient
	private int cod_avaliacao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public int getCod_avaliacao() {
		return cod_avaliacao;
	}
	public void setCod_avaliacao(int cod_avaliacao) {
		this.cod_avaliacao = cod_avaliacao;
	}
	
	
	
}