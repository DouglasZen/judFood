package br.com.douglas.restaurante.prato;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import br.com.douglas.restaurante.categoria.Categoria;

@Entity
@Table(name = "prato")
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
	
	
	public Prato(String descricao, Categoria categoria) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	public Prato(){
		super();
	}
	
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
	
	
	
	
	
	
}
