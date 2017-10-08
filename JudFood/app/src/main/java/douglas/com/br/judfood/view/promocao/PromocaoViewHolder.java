package douglas.com.br.judfood.view.promocao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import douglas.com.br.judfood.R;

/**
 * Created by Douglas on 02/10/2017.
 */

public class PromocaoViewHolder extends RecyclerView.ViewHolder{
    final TextView tv_promocao_nome;
    final ImageView iv_promocao_imagem;
    final View view;

    public PromocaoViewHolder(View view){
        super(view);
        this.view = view;
        this.iv_promocao_imagem = (ImageView) view.findViewById(R.id.promocao_imagem);
        this.tv_promocao_nome = (TextView) view.findViewById(R.id.promocao_nome);
    }
}
