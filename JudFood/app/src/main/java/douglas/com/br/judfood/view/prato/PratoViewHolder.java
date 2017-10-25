package douglas.com.br.judfood.view.prato;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    final ImageButton btFavorito;
    final ImageButton btDesfavorito;
    final View view;
    final TextView codigoPrato;
    final TextView codigoRestaurante;
    final TextView codigoFavorito;
    public PratoViewHolder(View view) {
        super(view);
        this.view = view;
        nome = (TextView) view.findViewById(R.id.tnome);
        imagem = (ImageView) view.findViewById(R.id.imageView);
        btFavorito = (ImageButton) view.findViewById(R.id.favorito);
        //descricao = (TextView) view.findViewById(R.id.tdescricao);
        codigoPrato = (TextView) view.findViewById(R.id.codigo_prato);
        codigoRestaurante = (TextView) view.findViewById(R.id.codigo_restaurante);
        btDesfavorito = (ImageButton) view.findViewById(R.id.desfavorito);
        codigoFavorito = (TextView) view.findViewById(R.id.codigo_favorito);
    }
}
