package douglas.com.br.judfood.restaurante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 28/09/2017.
 */

public class Restaurantes {
    List<Restaurante> restaurante = new ArrayList<Restaurante>();

    public List<Restaurante> getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(List<Restaurante> restaurante) {
        this.restaurante = restaurante;
    }
}
