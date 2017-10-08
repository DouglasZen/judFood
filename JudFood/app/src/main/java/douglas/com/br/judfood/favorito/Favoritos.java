package douglas.com.br.judfood.favorito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 26/09/2017.
 */

public class Favoritos {
    List<Favorito> favorito = new ArrayList<Favorito>();

    public List<Favorito> getFavorito() {
        return favorito;
    }

    public void setFavorito(List<Favorito> favorito) {
        this.favorito = favorito;
    }
}
