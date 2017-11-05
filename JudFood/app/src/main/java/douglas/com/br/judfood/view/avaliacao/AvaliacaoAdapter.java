package douglas.com.br.judfood.view.avaliacao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.avaliacao.Avaliacao;
import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.prato.Prato;


/**
 * Created by Douglas on 26/09/2017.
 */

public class AvaliacaoAdapter extends RecyclerView.Adapter{
    private List<Avaliacao> avaliacoes;
    private Context context;
    private final AvaliacaoonClickListener onClickListener;
    private List<Favorito> favoritos;

    public interface  AvaliacaoonClickListener{
        public void onClickAvaliacao(View view, int idx);
        public void onClickFavorito(View view, int idx);
        public void onClickDesFavorito(View view, int idx);
    }

    public AvaliacaoAdapter(List<Avaliacao> avaliacoes, Context context, AvaliacaoonClickListener onClickListener, List<Favorito> favoritos){
        this.context = context;
        this.avaliacoes = avaliacoes;
        this.onClickListener = onClickListener;
        this.favoritos = favoritos;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.avaliacao_adapter, parent, false);
        AvaliacaoViewHolder holder = new AvaliacaoViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position){
        final AvaliacaoViewHolder holder = (AvaliacaoViewHolder) viewHolder;
        boolean isFavorito = false;
        Prato prato = avaliacoes.get(position).getPrato();

        holder.nome.setText(String.valueOf(position + 1) + "º " + prato.getNome());
        holder.codigoPrato.setText(String.valueOf(prato.getId()));
        holder.codigoRestaurante.setText(String.valueOf(prato.getRestaurante().getCodigo()));
        for (Favorito favorito: favoritos) {
            if(favorito.getPrato().getId() == prato.getId()){
                holder.codigoFavorito.setText(String.valueOf(favorito.getCodigo()));
                isFavorito = true;
                break;
            }
        }

        if(isFavorito){
            holder.btDesfavorito.setVisibility(View.VISIBLE);
            holder.btDesfavorito.setColorFilter(Color.rgb(255,255,0));
            holder.btFavorito.setVisibility(View.INVISIBLE);
        }else{
            holder.btDesfavorito.setVisibility(View.INVISIBLE);
            holder.btFavorito.setVisibility(View.VISIBLE);
        }
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClickListener.onClickAvaliacao(holder.view, position);
                }
            });

            holder.btFavorito.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    onClickListener.onClickFavorito(holder.view, position);
                }

            });

            holder.btDesfavorito.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    onClickListener.onClickDesFavorito(holder.view, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return avaliacoes.size();
    }
}
