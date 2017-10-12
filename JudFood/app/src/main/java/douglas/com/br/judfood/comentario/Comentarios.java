package douglas.com.br.judfood.comentario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 08/10/2017.
 */

public class Comentarios {
    List<Comentario> comentario = new ArrayList<Comentario>();

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }
}
