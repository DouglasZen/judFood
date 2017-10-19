package br.com.douglas.restaurante.promocao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.douglas.restaurante.restaurante.Restaurante;

@Entity
@Table(name = "promocao")
public class Promocao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
	@Column(name="descricao")
	private String descricao;
	@Column(name="dataIni")
	private Date data_inicio;
	@Transient
	private String data_inicio_str;
	@Column(name="dataFim")
	private Date data_fim;
	@Transient
	private String data_fim_str;
	@ManyToOne
	@JoinColumn(name="codigo_restaurante")
	private Restaurante restaurante;
	@Column(name="imagem")
	private String imagem;
	@Column(name="titulo")
	private String titulo;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getData_inicio_str() {
		return data_inicio_str;
	}
	public void setData_inicio_str(String data_inicio_str) {
		this.data_inicio_str = data_inicio_str;
	}
	public String getData_fim_str() {
		return data_fim_str;
	}
	public void setData_fim_str(String data_fim_str) {
		this.data_fim_str = data_fim_str;
	}
	
	
}
