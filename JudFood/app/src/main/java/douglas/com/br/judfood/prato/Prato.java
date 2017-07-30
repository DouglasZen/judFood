package douglas.com.br.judfood.prato;

import java.io.Serializable;

import douglas.com.br.judfood.categoria.Categoria;

/**
 * Created by Douglas on 05/06/2017.
 */

public class Prato implements Serializable {
    private Integer id;
    private String descricao;
    private Categoria categoria;
    private String imagem;
    private String nome;

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

    public String toString(){
        return "Código " + getId() + " - " + getDescricao();
    }
}