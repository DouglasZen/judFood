package douglas.com.br.judfood.view.comentario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.comentario.Comentario;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.service.IComentarioService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.prato.PratoActivity;
import douglas.com.br.judfood.view.prato.PratoIntegraActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentarioActivity extends AppCompatActivity {
    private TextView codigoComentario;
    private TextView codigoAutor;
    private TextView codigoPrato;
    private TextView codigoRestaurante;
    private TextView autor;
    private TextView textoComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            int codcomentario = Integer.parseInt(extras.get("codigo").toString());
            getComentario(codcomentario);
        }
    }

    public void voltar(View view){
        codigoPrato = (TextView) findViewById(R.id.resposta_prato_codigo);
        Intent i = new Intent(ComentarioActivity.this, PratoIntegraActivity.class);
        i.putExtra("codigo_prato", codigoPrato.getText().toString());
        startActivity(i);
    }
    public void getComentario(int codComentario){
        codigoComentario = (TextView) findViewById(R.id.resposta_comentario_codigo);
        codigoAutor = (TextView) findViewById(R.id.resposta_comentario_codigo_autor);
        autor = (TextView) findViewById(R.id.resposta_comentario_autor);
        textoComentario = (TextView) findViewById(R.id.resposta_texto_comentario);
        codigoPrato = (TextView) findViewById(R.id.resposta_prato_codigo);
        codigoRestaurante = (TextView) findViewById(R.id.resposta_restaurante_codigo);
        IComentarioService service = ServiceGenerator.createService(IComentarioService.class);
        final Call<Comentario> call = service.getComentario(codComentario);

        call.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                if(response.isSuccessful()){
                    Comentario comentario = response.body();
                    codigoComentario.setText(String.valueOf(comentario.getCodigo()));
                    if(comentario.getPessoa() == null){
                        codigoAutor.setText(String.valueOf(comentario.getRestaurante().getCodigo()));
                        autor.setText(comentario.getRestaurante().getNome());
                    }else{
                        codigoAutor.setText(String.valueOf(comentario.getPessoa().getCodigo()));
                        autor.setText(comentario.getPessoa().getNome());
                    }

                    codigoPrato.setText(String.valueOf(comentario.getPrato().getId()));
                    codigoRestaurante.setText(String.valueOf(comentario.getRestaurante().getCodigo()));

                    textoComentario.setText(comentario.getComentario());

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.resposta_lista_comment_save);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(ComentarioActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new ComentarioAdapter(comentario.getRespostas(), ComentarioActivity.this, null));
                }
            }

            @Override
            public void onFailure(Call<Comentario> call, Throwable t) {
                Log.e("ERRO_COMENTARIO" , t.getMessage());
            }
        });

    }

    public void responder(View view){
        EditText textoResposta = (EditText) findViewById(R.id.resposta_text_comment);
        codigoPrato = (TextView) findViewById(R.id.resposta_prato_codigo);
        codigoRestaurante = (TextView) findViewById(R.id.resposta_restaurante_codigo);
        codigoComentario = (TextView) findViewById(R.id.resposta_comentario_codigo);
        String texto = textoResposta.getText().toString();
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(Prefs.getCodigoPessoa(ComentarioActivity.this,"codigoPessoa"));
        Prato prato = new Prato();
        prato.setId(Integer.parseInt(codigoPrato.getText().toString()));
        Restaurante restaurante = new Restaurante();
        restaurante.setCodigo(Integer.parseInt(codigoRestaurante.getText().toString()));
        Comentario comentario = new Comentario();
        comentario.setRestaurante(restaurante);
        comentario.setPrato(prato);
        comentario.setPessoa(pessoa);
        comentario.setComentario(texto);
        comentario.setCodComentario(codigoComentario.getText().toString());

        IComentarioService service = ServiceGenerator.createService(IComentarioService.class);
        final Call<Comentario> call = service.setResposta(comentario);
        call.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ComentarioActivity.this, "Agradecemos seu comentário", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Comentario> call, Throwable t) {

            }
        });


    }
}
