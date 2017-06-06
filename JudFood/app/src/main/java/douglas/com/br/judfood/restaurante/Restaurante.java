package douglas.com.br.judfood.restaurante;

import java.io.Serializable;

/**
 * Created by Douglas on 05/06/2017.
 */

public class Restaurante implements Serializable {

    private Integer codigo;
    private String nome;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
