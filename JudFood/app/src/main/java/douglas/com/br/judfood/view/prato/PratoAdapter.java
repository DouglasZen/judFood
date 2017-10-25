package douglas.com.br.judfood.view.prato;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.view.MainActivity;

/**
 * Created by Douglas on 03/07/2017.
 */

public class PratoAdapter extends RecyclerView.Adapter{

    private List<Prato> pratos;
    private Context context;
    private final PratoOnClickListener onClickListener;
    private List<Favorito> favoritos;

    public interface  PratoOnClickListener{
        public void onClickPrato(View view, int idx);
        public void onClickFavorito(View view, int idx);
        public void onClickDesfavorito(View view, int idx);

    }

    public PratoAdapter(List<Prato> pratos, Context context, PratoOnClickListener onClickListener, List<Favorito> favoritos){
        this.context = context;
        this.pratos = pratos;
        this.onClickListener = onClickListener;
        this.favoritos = favoritos;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.prato_adapter, parent, false);
        PratoViewHolder holder = new PratoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final PratoViewHolder holder = (PratoViewHolder) viewHolder;
        Prato prato = pratos.get(position);
        boolean isFavorito = false;
        byte[] image = Base64.decode(prato.getImagem(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        //holder.descricao.setText(prato.getDescricao());
        holder.imagem.setImageBitmap(bitmap);
        holder.nome.setText(prato.getNome());
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
               public void onClick(View v){
                   onClickListener.onClickPrato(holder.view, position);
               }
           });

           holder.btFavorito.setOnClickListener(new View.OnClickListener(){
               public void onClick(View view){
                   onClickListener.onClickFavorito(holder.view, position);
               }

           });
           holder.btDesfavorito.setOnClickListener(new View.OnClickListener(){
               public void onClick(View view){
                   onClickListener.onClickDesfavorito(holder.view, position);
               }
           });
       }
       isFavorito = false;

    }

    @Override
    public int getItemCount() {
        return pratos.size();
    }
}
