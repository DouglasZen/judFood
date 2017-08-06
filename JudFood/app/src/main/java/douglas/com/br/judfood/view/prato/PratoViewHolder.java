package douglas.com.br.judfood.view.prato;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 04/07/2017.
 */

public class PratoViewHolder extends RecyclerView.ViewHolder{
    final TextView nome;
    final ImageView imagem;
    //final TextView descricao;
    final View view;
    public PratoViewHolder(View view) {
        super(view);
        this.view = view;
        nome = (TextView) view.findViewById(R.id.tnome);
        imagem = (ImageView) view.findViewById(R.id.imageView);
        //descricao = (TextView) view.findViewById(R.id.tdescricao);
    }
}
