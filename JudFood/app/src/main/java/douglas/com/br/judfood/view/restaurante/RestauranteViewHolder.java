package douglas.com.br.judfood.view.restaurante;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 28/09/2017.
 */

public class RestauranteViewHolder extends RecyclerView.ViewHolder{
    final TextView nome_restaurante;
    final View view;
    final TextView codigo_restaurante;

    public RestauranteViewHolder(View view){
        super(view);
        this.view = view;
        nome_restaurante = (TextView) view.findViewById(R.id.restaurante_nome);
        codigo_restaurante = (TextView) view.findViewById(R.id.restaurante_codigo);
    }
}
