package douglas.com.br.judfood.view.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 16/08/2017.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder{

    final TextView codigo;
    final ImageView imagem;
    final TextView nome;
    final View view;
    final ImageView imagemCategoria;

    public HomeViewHolder(View view) {
        super(view);
        this.view = view;
        codigo = (TextView) view.findViewById(R.id.tcodigo);
        imagem = (ImageView) view.findViewById(R.id.imageView);
        nome = (TextView) view.findViewById(R.id.tnomeCategoria);
        imagemCategoria = (ImageView) view.findViewById(R.id.imagemCategoria);
    }
}
