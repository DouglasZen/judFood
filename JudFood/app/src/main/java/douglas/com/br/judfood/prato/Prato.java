package douglas.com.br.judfood.prato;

import java.io.Serializable;

import douglas.com.br.judfood.categoria.Categoria;
import douglas.com.br.judfood.restaurante.Restaurante;

/**
 * Created by Douglas on 05/06/2017.
 */

public class Prato implements Serializable {
    private Integer id;
    private String descricao;
    private Categoria categoria;
    private String imagem;
    private String nome;
    private Restaurante restaurante;
    private double media;
    private int cod_avaliacao;
    private int total_comentario;

    public Prato(Integer id, String descricao, Categoria categoria, String imagem) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public Prato(){

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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String toString(){
        return "Código " + getId() + " - " + getDescricao();
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

    public int getTotal_comentario() {
        return total_comentario;
    }

    public void setTotal_comentario(int total_comentario) {
        this.total_comentario = total_comentario;
    }
}
