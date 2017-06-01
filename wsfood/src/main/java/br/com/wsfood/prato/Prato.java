package br.com.wsfood.prato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.wsfood.categoria.Categoria;



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
	
	
}