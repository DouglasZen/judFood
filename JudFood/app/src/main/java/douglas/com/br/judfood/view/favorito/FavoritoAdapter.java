package douglas.com.br.judfood.view.favorito;

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
import douglas.com.br.judfood.favorito.Favorito;

/**
 * Created by Douglas on 26/09/2017.
 */

public class FavoritoAdapter extends RecyclerView.Adapter{

    private List<Favorito> favoritos;
    private Context context;
    private final FavoritoOnClickListener onClickListerner;

    public interface FavoritoOnClickListener{
        public void onClickFavoritar(View view, int idx);
        public void onClickIntegra(View view, int idx);
    }

    public FavoritoAdapter(List<Favorito> favoritos, Context context, FavoritoOnClickListener onClickListener){
        this.context = context;
        this.favoritos = favoritos;
        this.onClickListerner = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorito_adapter, parent, false);
        FavoritoViewHolder holder = new FavoritoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final FavoritoViewHolder holder = (FavoritoViewHolder) viewHolder;
        Favorito favorito = favoritos.get(position);
        byte[] image = Base64.decode(favorito.getPrato().getImagem(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.imagem.setImageBitmap(bitmap);
        holder.nome.setText(favorito.getPrato().getNome());
        holder.codFavorito.setText(String.valueOf(favorito.getCodigo()));
        holder.codPrato.setText(String.valueOf(favorito.getPrato().getId()));
        holder.btFavoritar.setColorFilter(Color.rgb(255,255,0));
        if(onClickListerner != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    onClickListerner.onClickIntegra(holder.view, position);
                }
            });

            holder.btFavoritar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    onClickListerner.onClickFavoritar(holder.view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }
}
