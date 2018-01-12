package douglas.com.br.judfood.view.prato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
import douglas.com.br.judfood.view.comentario.ComentarioActivity;
import douglas.com.br.judfood.R;
import douglas.com.br.judfood.avaliacao.Avaliacao;
import douglas.com.br.judfood.comentario.Comentario;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.service.IAvaliacaoService;
import douglas.com.br.judfood.service.IComentarioService;
import douglas.com.br.judfood.service.IPratoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.comentario.ComentarioAdapter;
import douglas.com.br.judfood.view.favorito.FavoritoActivity;
import douglas.com.br.judfood.view.restaurante.RestauranteActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PratoIntegraActivity extends AppCompatActivity {
    private TextView tv_nome_prato;
    private TextView tv_descricao_prato;
    private TextView tv_codigo_prato;
    private TextView tv_codigo_restaurante;
    private ImageView iv_image_prato;
    private TextView tv_codigo_avaliacao;
    private TextView tv_total_comentario;
    private TextView tv_codigo_categoria;
    String origem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prato_integra);

        Bundle extras = getIntent().getExtras();
        int tamanho = extras.size();
        if(extras != null){
            getPrato(extras.getString("codigo_prato").toString());
            if(tamanho > 1 && null != extras.getString("origem"))
                origem = extras.getString("origem").toString();
            //Toast.makeText(this, extras.getString("codigo_prato").toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void voltar(View view){
        tv_codigo_restaurante = (TextView) findViewById(R.id.integra_codigo_restaurante);
        Intent i = null;
        if(null != origem){
            if("favorito".equals(origem)){
                i = new Intent(PratoIntegraActivity.this, FavoritoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }else if("ranking".equals(origem)){
                i = new Intent(PratoIntegraActivity.this, AvaliacaoActivity.class);
                i.putExtra("codigoCategoria", tv_codigo_categoria.getText().toString());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }

        }else{
            i = new Intent(PratoIntegraActivity.this, PratoActivity.class);
            i.putExtra("codigo_restaurante", tv_codigo_restaurante.getText().toString());
        }

        startActivity(i);
    }
    public void getPrato(String codigo_prato){
        int codigoPessoa = Prefs.getCodigoPessoa(PratoIntegraActivity.this,"codigoPessoa");
        IPratoService service = ServiceGenerator.createService(IPratoService.class);
        final Call<Prato> call = service.getPrato(Integer.parseInt(codigo_prato), codigoPessoa);
        call.enqueue(new Callback<Prato>() {
            @Override
            public void onResponse(Call<Prato> call, Response<Prato> response) {
                if(response.isSuccessful()){

                    Prato prato = response.body();
                    byte[] image = Base64.decode(prato.getImagem(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

                    iv_image_prato = (ImageView) findViewById(R.id.integra_image_prato);
                    tv_nome_prato = (TextView) findViewById(R.id.integra_nome);
                    tv_descricao_prato = (TextView) findViewById(R.id.integra_descricao);
                    tv_codigo_prato = (TextView) findViewById(R.id.integra_codigo_prato);
                    tv_codigo_restaurante = (TextView) findViewById(R.id.integra_codigo_restaurante);
                    tv_codigo_avaliacao = (TextView) findViewById(R.id.integra_codigo_avaliacao);
                    tv_total_comentario = (TextView) findViewById(R.id.integra_qtd_comment);
                    tv_codigo_categoria = (TextView) findViewById(R.id.integra_codigo_categoria);

                    iv_image_prato.setImageBitmap(bitmap);
                    tv_codigo_prato.setText(prato.getId().toString());
                    tv_codigo_restaurante.setText(prato.getRestaurante().getCodigo().toString());
                    tv_nome_prato.setText(prato.getNome());
                    tv_descricao_prato.setText(prato.getDescricao());
                    tv_codigo_avaliacao.setText(String.valueOf(prato.getCod_avaliacao()));
                    tv_total_comentario.setText(String.valueOf(prato.getTotal_comentario()));
                    tv_codigo_categoria.setText(String.valueOf(prato.getCategoria().getCodigo()));
                    if(prato.getMedia() > 0)
                        setEstrela(prato.getMedia());

                }
            }

            @Override
            public void onFailure(Call<Prato> call, Throwable t) {
                t.printStackTrace();
                Log.e("ERRO",  t.getMessage());
            }
        });
    }

    public void botaoEstrela(View view){
        ImageButton star;
        switch (view.getId()){
            case R.id.integra_star_1:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                calcular(1);
                break;
            case R.id.integra_star_2:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                calcular(2);
                break;
            case R.id.integra_star_3:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                calcular(3);
                break;
            case R.id.integra_star_4:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                calcular(4);
                break;
            case R.id.integra_star_5:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,0));
                calcular(5);
                break;

        }
    }

    public void calcular(int botao){
        tv_codigo_avaliacao = (TextView) findViewById(R.id.integra_codigo_avaliacao);
        tv_codigo_prato = (TextView) findViewById(R.id.integra_codigo_prato);
        int codigoAvaliacao = 0;

        if(!"0".equals(tv_codigo_avaliacao.getText())) {
            codigoAvaliacao = Integer.parseInt(tv_codigo_avaliacao.getText().toString());
        }
        int codigoPrato = Integer.parseInt(tv_codigo_prato.getText().toString());
        int codigoPessoa = Prefs.getCodigoPessoa(PratoIntegraActivity.this,"codigoPessoa");
        double nota = botao * 2.0;
        Prato prato = new Prato();
        prato.setId(codigoPrato);
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(codigoPessoa);
        Avaliacao avaliacao = new Avaliacao();
        if(codigoAvaliacao != 0){
            avaliacao.setCodigo(codigoAvaliacao);
        }
        avaliacao.setPrato(prato);
        avaliacao.setPessoa(pessoa);
        avaliacao.setNota(nota);
        avaliar(avaliacao);
    }

    public void avaliar(Avaliacao avaliacao){
        IAvaliacaoService service = ServiceGenerator.createService(IAvaliacaoService.class);
        final Call<Avaliacao> call = service.setAvaliacao(avaliacao);

        call.enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                if(response.isSuccessful()){
                    Avaliacao a = response.body();
                    tv_codigo_avaliacao = (TextView) findViewById(R.id.integra_codigo_avaliacao);
                    tv_codigo_avaliacao.setText(a.getCodigo().toString());

                }
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Log.e("ERRO_FAVORITO" , t.getMessage());
            }
        });
    }

    public void abrirComentario(View view){
        EditText et_comentario = (EditText) findViewById(R.id.integra_text_comment);
        et_comentario.setVisibility(View.VISIBLE);
        ImageButton ib_send = (ImageButton) findViewById(R.id.integra_send_comment);
        ib_send.setVisibility(View.VISIBLE);
        TextView vertodos = (TextView) findViewById(R.id.integra_ver_todos);
        vertodos.setVisibility(View.VISIBLE);
        listarComentario(10);
    }

    public void vermais(View view){
        TextView vertodos = (TextView) findViewById(R.id.integra_ver_todos);
        vertodos.setVisibility(View.INVISIBLE);
        TextView vermenos = (TextView) findViewById(R.id.integra_ver_menos);
        vermenos.setVisibility(View.VISIBLE);
        listarComentario(0);
    }

    public void vermenos(View view){
        TextView vertodos = (TextView) findViewById(R.id.integra_ver_todos);
        vertodos.setVisibility(View.VISIBLE);
        TextView vermenos = (TextView) findViewById(R.id.integra_ver_menos);
        vermenos.setVisibility(View.INVISIBLE);
        listarComentario(10);
    }

    public void comentar(View view){
        tv_codigo_prato = (TextView) findViewById(R.id.integra_codigo_prato);
        tv_codigo_restaurante = (TextView) findViewById(R.id.integra_codigo_restaurante);
        final TextView tv_comentario = (TextView) findViewById(R.id.integra_text_comment);
        int codigo_prato = Integer.parseInt(tv_codigo_prato.getText().toString());
        int codigo_restaurante = Integer.parseInt(tv_codigo_restaurante.getText().toString());
        int codigoPessoa = Prefs.getCodigoPessoa(PratoIntegraActivity.this,"codigoPessoa");
        String texto_comentario = tv_comentario.getText().toString();

        Restaurante restaurante = new Restaurante();
        restaurante.setCodigo(codigo_restaurante);
        Prato prato = new Prato();
        prato.setId(codigo_prato);
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(codigoPessoa);
        Comentario comentario = new Comentario();
        comentario.setPessoa(pessoa);
        comentario.setPrato(prato);
        comentario.setRestaurante(restaurante);
        comentario.setComentario(texto_comentario);

        IComentarioService service = ServiceGenerator.createService(IComentarioService.class);
        final Call<Comentario> call = service.setComentario(comentario);

        call.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PratoIntegraActivity.this, "Agradecemos seu comentário", Toast.LENGTH_SHORT).show();
                    listarComentario(10);
                    tv_comentario.setText("");
                }
            }

            @Override
            public void onFailure(Call<Comentario> call, Throwable t) {
                Log.e("ERRO_FAVORITO" , t.getMessage());
            }
        });
    }

    public void listarComentario(int max){
        tv_codigo_prato = (TextView) findViewById(R.id.integra_codigo_prato);
        int codigo_prato = Integer.parseInt(tv_codigo_prato.getText().toString());

        IComentarioService service = ServiceGenerator.createService(IComentarioService.class);
        final Call <List<Comentario>> call = service.listaComentarios(codigo_prato, max);

        call.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                if(response.isSuccessful()){
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.integra_lista_comment_save);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(PratoIntegraActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new ComentarioAdapter(response.body(), PratoIntegraActivity.this, onClickResponder()));
                }
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {
                Log.e("ERRO_COMENTARIO" , t.getMessage());
            }
        });
    }

    private ComentarioAdapter.ComentarioOnClickListener onClickResponder(){
        return new ComentarioAdapter.ComentarioOnClickListener(){
            @Override
            public void onClickResponder(View view, int idx) {
                TextView tv_codcomentario = (TextView) view.findViewById(R.id.comentario_codigo);
                int codComentario = Integer.parseInt(tv_codcomentario.getText().toString());

                //Toast.makeText(PratoIntegraActivity.this, String.valueOf(codComentario), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PratoIntegraActivity.this, ComentarioActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("codigo", codComentario);
                startActivity(intent);
            }
        };
    }
    public void setEstrela(double media){
        ImageButton star;
        int estrelas = (int) (media / 2);
        switch (estrelas){
            case 1:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                break;
            case 2:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                break;
            case 3:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,255));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                break;
            case 4:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,255));
                break;
            case 5:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(255,255,0));
                break;

        }

    }

}
