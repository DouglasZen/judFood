package douglas.com.br.judfood.categoria;

import java.io.Serializable;

import douglas.com.br.judfood.restaurante.Restaurante;

/**
 * Created by Douglas on 05/06/2017.
 */

public class Categoria implements Serializable {
    private Integer codigo;
    private String descricao;
    private Restaurante restaurante;
    private String imagem;

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
}
