package douglas.com.br.judfood.view.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.categoria.Categoria;

/**
 * Created by Douglas on 16/08/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter{

    private List<Categoria> categorias;
    private Context context;
    private final CategoriaOnClickListener onClickListener;

    public interface CategoriaOnClickListener{
        public void onClickCategoria(View view, int idx);
    }

    public HomeAdapter(List<Categoria> categorias, Context context, CategoriaOnClickListener onClickListener){
        this.context = context;
        this.categorias = categorias;
        this.onClickListener = onClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_adapter, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final HomeViewHolder holder = (HomeViewHolder) viewHolder;
        Categoria categoria = categorias.get(position);
        if(categoria.getImagem() != null) {
            byte[] image = Base64.decode(categoria.getImagem(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

            holder.imagem.setImageBitmap(bitmap);

        }
        holder.codigo.setText(categoria.getCodigo().toString());
        holder.nome.setText(categoria.getDescricao().toString());

        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    onClickListener.onClickCategoria(holder.view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}
