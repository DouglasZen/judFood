package br.com.wsfood.comentario;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wsfood.pessoa.Pessoa;
import br.com.wsfood.prato.Prato;
import br.com.wsfood.restaurante.Restaurante;

@Entity
@Table(name="comentario")
@XmlRootElement

public class Comentario implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
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
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="codigo_comentario")
	private List<Comentario> respostas;
	@Column(name="codigo_comentario")
	private String codComentario;
	@Transient
	private int total;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
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
	
	public List<Comentario> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Comentario> respostas) {
		this.respostas = respostas;
	}
	public String getCodComentario() {
		return codComentario;
	}
	public void setCodComentario(String codComentario) {
		this.codComentario = codComentario;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
}
