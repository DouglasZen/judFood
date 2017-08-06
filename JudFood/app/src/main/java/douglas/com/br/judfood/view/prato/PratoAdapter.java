package douglas.com.br.judfood.view.prato;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.view.MainActivity;

/**
 * Created by Douglas on 03/07/2017.
 */

public class PratoAdapter extends RecyclerView.Adapter{

    private List<Prato> pratos;
    private Context context;
    private final PratoOnClickListener onClickListener;

    public interface  PratoOnClickListener{
        public void onClickPrato(View view, int idx);
    }

    public PratoAdapter(List<Prato> pratos, Context context, PratoOnClickListener onClickListener){
        this.context = context;
        this.pratos = pratos;
        this.onClickListener = onClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.prato_adapter, parent, false);
        PratoViewHolder holder = new PratoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final PratoViewHolder holder = (PratoViewHolder) viewHolder;
        Prato prato = pratos.get(position);
        byte[] image = Base64.decode(prato.getImagem(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        //holder.descricao.setText(prato.getDescricao());
        holder.imagem.setImageBitmap(bitmap);
        holder.nome.setText(prato.getNome());

       if(onClickListener != null){
           holder.itemView.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v){
                   onClickListener.onClickPrato(holder.view, position);
               }
           });
       }


    }

    @Override
    public int getItemCount() {
        return pratos.size();
    }
}
