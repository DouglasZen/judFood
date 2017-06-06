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

    public String toString(){
        return "Código " + getId() + " - " + getDescricao();
    }
}
