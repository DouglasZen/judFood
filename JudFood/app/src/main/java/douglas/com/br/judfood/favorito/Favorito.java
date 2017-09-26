package douglas.com.br.judfood.favorito;

import java.io.Serializable;

import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;

/**
 * Created by Douglas on 28/08/2017.
 */

public class Favorito implements Serializable {
    private Integer codigo;
    private Pessoa pessoa;
    private Prato prato;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }
}
