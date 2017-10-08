package douglas.com.br.judfood.view.prato;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.avaliacao.Avaliacao;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.service.IAvaliacaoService;
import douglas.com.br.judfood.service.IPratoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prato_integra);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            getPrato(extras.getString("codigo_prato").toString());
            //Toast.makeText(this, extras.getString("codigo_prato").toString(), Toast.LENGTH_SHORT).show();
        }

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

                    iv_image_prato.setImageBitmap(bitmap);
                    tv_codigo_prato.setText(prato.getId().toString());
                    tv_codigo_restaurante.setText(prato.getRestaurante().getCodigo().toString());
                    tv_nome_prato.setText(prato.getNome());
                    tv_descricao_prato.setText(prato.getDescricao());
                    tv_codigo_avaliacao.setText(String.valueOf(prato.getCod_avaliacao()));
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
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
                calcular(1);
                break;
            case R.id.integra_star_2:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
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
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
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
                star.setColorFilter(Color.rgb(0,0,0));
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

    public void setEstrela(double media){
        ImageButton star;
        int estrelas = (int) (media / 2);
        switch (estrelas){
            case 1:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
                break;
            case 2:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
                break;
            case 3:
                star = (ImageButton) findViewById(R.id.integra_star_1);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_2);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_3);
                star.setColorFilter(Color.rgb(255,255,0));
                star = (ImageButton) findViewById(R.id.integra_star_4);
                star.setColorFilter(Color.rgb(0,0,0));
                star = (ImageButton) findViewById(R.id.integra_star_5);
                star.setColorFilter(Color.rgb(0,0,0));
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
                star.setColorFilter(Color.rgb(0,0,0));
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
