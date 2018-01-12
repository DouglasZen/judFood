package douglas.com.br.judfood.view.promocao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.promocao.Promocao;

/**
 * Created by Douglas on 02/10/2017.
 */

public class PromocaoAdapter extends RecyclerView.Adapter{

    private List<Promocao> promocoes;
    private Context context;

    public PromocaoAdapter(List<Promocao> promocoes, Context context){
        this.promocoes = promocoes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.promocao_adapter, parent, false);
        PromocaoViewHolder holder = new PromocaoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PromocaoViewHolder holder = (PromocaoViewHolder) viewHolder;
        Promocao promocao = promocoes.get(position);
        byte[] image = Base64.decode(promocao.getImagem(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        //holder.iv_promocao_imagem.setImageBitmap(bitmap);
        holder.iv_promocao_imagem.setBackground(new BitmapDrawable(bitmap));
        holder.tv_promocao_nome.setText(promocao.getDescricao());
        holder.tv_promocao_restaurante.setText(promocao.getRestaurante().getNome());
    }

    @Override
    public int getItemCount() {
        return promocoes.size();
    }
}
