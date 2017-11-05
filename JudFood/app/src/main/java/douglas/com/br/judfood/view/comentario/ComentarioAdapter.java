package douglas.com.br.judfood.view.comentario;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.comentario.Comentario;

/**
 * Created by Douglas on 09/10/2017.
 */

public class ComentarioAdapter extends RecyclerView.Adapter{
    private List<Comentario> comentarios;
    private Context context;
    private final ComentarioOnClickListener onClickListener;

    public interface ComentarioOnClickListener{
        public void onClickResponder(View view, int idx);
    }

    public ComentarioAdapter(List<Comentario> comentarios, Context context, ComentarioOnClickListener onClickListener){
        this.context = context;
        this.comentarios = comentarios;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comentario_adapter, parent, false);
        ComentarioViewHolder holder = new ComentarioViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ComentarioViewHolder holder = (ComentarioViewHolder) viewHolder;

        Comentario comentario = comentarios.get(position);
        if(comentario.getPessoa() != null) {
            holder.codigo_autor.setText(String.valueOf(comentario.getPessoa().getCodigo()));
            holder.autor.setText(comentario.getPessoa().getNome());
        }else{
            holder.codigo_autor.setText(String.valueOf(comentario.getRestaurante().getCodigo()));
            holder.autor.setText(comentario.getRestaurante().getNome());
        }
        holder.codigo_comentario.setText(String.valueOf(comentario.getCodigo()));

        holder.texto.setText(comentario.getComentario());
        if(comentario.getTotal() > 0){
            if(comentario.getTotal() == 1)
                holder.total.setText(comentario.getTotal() + " resposta");
            else
                holder.total.setText(comentario.getTotal() + " respostas");
        }
        holder.total.setTypeface(null, Typeface.BOLD);
        if(onClickListener != null){
            holder.responder.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    onClickListener.onClickResponder(holder.view, position);
                }
            });
        }else{
            holder.responder.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }


}
