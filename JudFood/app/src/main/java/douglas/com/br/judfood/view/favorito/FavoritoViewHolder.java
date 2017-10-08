package douglas.com.br.judfood.view.favorito;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 26/09/2017.
 */

public class FavoritoViewHolder extends RecyclerView.ViewHolder{
    final ImageView imagem;
    final TextView nome;
    final View view;
    final ImageButton btFavoritar;
    public FavoritoViewHolder(View view) {
        super(view);
        this.view = view;
        imagem = (ImageView) view.findViewById(R.id.favorito_imagem);
        nome = (TextView) view.findViewById(R.id.favorito_nome);
        btFavoritar = (ImageButton) view.findViewById(R.id.favorito_estrela_imagem);

    }
}
