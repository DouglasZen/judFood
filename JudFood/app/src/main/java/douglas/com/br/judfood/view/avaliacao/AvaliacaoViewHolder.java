package douglas.com.br.judfood.view.avaliacao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 26/09/2017.
 */

public class AvaliacaoViewHolder extends RecyclerView.ViewHolder{
    final TextView nome;
    final ImageView imagem;
    final TextView descricao;
    final ImageButton btFavorito;
    final View view;
    final TextView codigoPrato;
    final TextView codigoRestaurante;
    public AvaliacaoViewHolder(View view) {
        super(view);
        this.view = view;
        nome = (TextView) view.findViewById(R.id.a_tnome);
        imagem = (ImageView) view.findViewById(R.id.a_imageView);
        btFavorito = (ImageButton) view.findViewById(R.id.a_favorito);
        descricao = (TextView) view.findViewById(R.id.a_tdescricao);
        codigoPrato = (TextView) view.findViewById(R.id.a_codigo_prato);
        codigoRestaurante = (TextView) view.findViewById(R.id.a_codigo_restaurante);
    }
}
