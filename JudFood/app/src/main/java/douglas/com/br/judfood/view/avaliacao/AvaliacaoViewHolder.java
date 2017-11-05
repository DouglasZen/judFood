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
    final ImageButton btFavorito;
    final ImageButton btDesfavorito;
    final View view;
    final TextView codigoPrato;
    final TextView codigoRestaurante;
    final TextView codigoFavorito;
    public AvaliacaoViewHolder(View view) {
        super(view);
        this.view = view;
        nome = (TextView) view.findViewById(R.id.a_tnome);
        btFavorito = (ImageButton) view.findViewById(R.id.a_favorito);
        btDesfavorito = (ImageButton) view.findViewById(R.id.a_desfavorito);
        codigoPrato = (TextView) view.findViewById(R.id.a_codigo_prato);
        codigoRestaurante = (TextView) view.findViewById(R.id.a_codigo_restaurante);
        codigoFavorito = (TextView) view.findViewById(R.id.a_codigo_favorito);
    }
}
