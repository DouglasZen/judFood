package douglas.com.br.judfood.view.comentario;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 09/10/2017.
 */

public class ComentarioViewHolder extends RecyclerView.ViewHolder{
    final TextView codigo_comentario;
    final TextView codigo_autor;
    final TextView autor;
    final TextView texto;
    final TextView responder;
    final View view;

    public ComentarioViewHolder(View view){
        super(view);
        this.view = view;
        codigo_comentario = (TextView) view.findViewById(R.id.comentario_codigo);
        codigo_autor = (TextView) view.findViewById(R.id.comentario_codigo_autor);
        autor = (TextView) view.findViewById(R.id.comentario_autor);
        texto = (TextView) view.findViewById(R.id.comentario_texto);
        responder = (TextView) view.findViewById(R.id.comentario_responder);
    }
}
