package douglas.com.br.judfood.view.restaurante;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.restaurante.Restaurante;

/**
 * Created by Douglas on 28/09/2017.
 */

public class RestauranteAdapter extends RecyclerView.Adapter{

    private List<Restaurante> restaurantes;
    private Context context;
    private final RestauranteOnClickListener onClickListener;

    public interface RestauranteOnClickListener{
        public void onClickRestaurante(View view, int idx);
    }

    public RestauranteAdapter(List<Restaurante> restaurantes, Context context, RestauranteOnClickListener onClickListener) {
        this.restaurantes = restaurantes;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurante_adapter, parent, false);
        RestauranteViewHolder holder = new RestauranteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final RestauranteViewHolder holder = (RestauranteViewHolder) viewHolder;
        Restaurante restaurante = restaurantes.get(position);
        holder.nome_restaurante.setText(restaurante.getNome());
        holder.codigo_restaurante.setText(String.valueOf(restaurante.getCodigo()));

        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    onClickListener.onClickRestaurante(holder.view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }
}
