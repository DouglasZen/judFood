package douglas.com.br.judfood.pessoa;

import java.io.Serializable;

/**
 * Created by Douglas on 29/07/2017.
 */

public class Pessoa implements Serializable {
    private Integer codigo;
    private String nome;
    private String email;
    private String idFacebook;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idfacebook) {
        this.idFacebook = idfacebook;
    }
}
