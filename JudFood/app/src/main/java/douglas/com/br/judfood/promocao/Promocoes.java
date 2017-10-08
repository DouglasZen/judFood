package douglas.com.br.judfood.promocao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 02/10/2017.
 */

public class Promocoes {
    List<Promocao> promocao = new ArrayList<Promocao>();

    public List<Promocao> getPromocao() {
        return promocao;
    }

    public void setPromocao(List<Promocao> promocao) {
        this.promocao = promocao;
    }
}
