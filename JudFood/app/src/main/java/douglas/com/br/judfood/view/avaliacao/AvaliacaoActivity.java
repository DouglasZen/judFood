package douglas.com.br.judfood.view.avaliacao;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.avaliacao.Avaliacao;
import douglas.com.br.judfood.avaliacao.Avaliacoes;
import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.service.IAvaliacaoService;
import douglas.com.br.judfood.service.IFavoritoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.AtualizaFav;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.home.HomeActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import douglas.com.br.judfood.view.prato.PratoActivity;
import douglas.com.br.judfood.view.restaurante.RestauranteActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class AvaliacaoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Avaliacao> listAvaliacao = new ArrayList<Avaliacao>();
    private boolean cardOpen = false;
    private int originalHeight = 0;
    ImageView iv;
    TextView tvDesc;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            goLogin();
        }
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            listarAvaliacoes(extras.getString("codigoCategoria").toString());
        }
    }

    public void voltar(View view){
        Intent intent = new Intent(AvaliacaoActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarAvaliacoes(String codCategoria){
        final List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

        IAvaliacaoService service = ServiceGenerator.createService(IAvaliacaoService.class);

        final Call<List<Avaliacao>> call = service.listranking(Integer.parseInt(codCategoria));

        call.enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                if(response.isSuccessful()){
                    List<Avaliacao> a = new ArrayList<Avaliacao>();
                    a = (List<Avaliacao>) response.body();
                    if(!a.isEmpty()){
                        avaliacoes.addAll(a);
                        String f = Prefs.getFavoritos(AvaliacaoActivity.this, "favoritos");
                        Type listType = new TypeToken<List<Favorito>>(){}.getType();
                        Gson gson = new Gson();
                        List<Favorito> favoritos = (List<Favorito>) gson.fromJson(f, listType);
                        recyclerView = (RecyclerView) findViewById(R.id.listaranking);
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(AvaliacaoActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layout);
                        recyclerView.setAdapter(new AvaliacaoAdapter(avaliacoes, AvaliacaoActivity.this, onClickAvaliacao(), favoritos));
                    }else{
                        Intent intent = new Intent(AvaliacaoActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(AvaliacaoActivity.this, "Ainda não temos ranking para essa categoria", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Log.e("ERRO RESPONSE",  response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                t.printStackTrace();
                Log.e("ERRO",  t.getMessage());
            }
        });
    }

    private AvaliacaoAdapter.AvaliacaoonClickListener onClickAvaliacao(){
        return new AvaliacaoAdapter.AvaliacaoonClickListener(){
            public void onClickAvaliacao(final  View view, int idx){
                ValueAnimator valueAnimator;
                iv = (ImageView) view.findViewById(R.id.a_imageView);
                tvDesc = (TextView) view.findViewById(R.id.a_tdescricao);
                ImageButton star = (ImageButton) view.findViewById(R.id.a_favorito);
                if (originalHeight == 0) {
                    originalHeight = view.getHeight();
                }
                if(!cardOpen){
                    iv.setVisibility(VISIBLE);
                    tvDesc.setVisibility(VISIBLE);
                    cardOpen = true;
                    valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 2));
                }else{
                    cardOpen = false;
                    valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 2), originalHeight);
                    Animation a = new AlphaAnimation(1.00f, 0.00f);
                    a.setDuration(200);
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            iv.setVisibility(INVISIBLE);
                            tvDesc.setVisibility(INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    cv = (CardView) view.findViewById(R.id.cardAvaliacao);
                    cv.startAnimation(a);
                }
                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        view.getLayoutParams().height = value.intValue();
                        view.requestLayout();
                    }
                });
                valueAnimator.start();

            }

            @Override
            public void onClickFavorito(View view, int idx) {
                ImageButton favoritar = (ImageButton)  view.findViewById(R.id.a_favorito);
                ImageButton desfavoritar = (ImageButton)  view.findViewById(R.id.a_desfavorito);
                TextView codigo = (TextView) view.findViewById(R.id.a_codigo_prato);
                TextView codigoRestaurante = (TextView) view.findViewById(R.id.a_codigo_restaurante);

                int codigoPessoa = Prefs.getCodigoPessoa(AvaliacaoActivity.this,"codigoPessoa");
                favoritar(codigoPessoa, Integer.parseInt(codigo.getText().toString()), Integer.parseInt(codigoRestaurante.getText().toString()), view);

                favoritar.setVisibility(INVISIBLE);
                desfavoritar.setVisibility(VISIBLE);
                desfavoritar.setColorFilter(Color.rgb(255,255,0));
            }

            public void onClickDesFavorito(View view, int idx){
                ImageButton favoritar = (ImageButton)  view.findViewById(R.id.a_favorito);
                ImageButton desfavoritar = (ImageButton)  view.findViewById(R.id.a_desfavorito);
                favoritar.setVisibility(VISIBLE);
                desfavoritar.setVisibility(INVISIBLE);
                TextView codigoFavorito = (TextView) view.findViewById(R.id.a_codigo_favorito);
                desfavoritar(codigoFavorito.getText().toString());
            }
        };
    }

    public void favoritar(int codigoPessoa, int codigoPrato, int codigoRestaurante, final View view){
        Pessoa p = new Pessoa();
        p.setCodigo(codigoPessoa);
        Prato prato = new Prato();
        prato.setId(codigoPrato);
        Restaurante r = new Restaurante();
        r.setCodigo(codigoRestaurante);

        Favorito favorito = new Favorito();
        favorito.setPessoa(p);
        favorito.setPrato(prato);

        IFavoritoService service = ServiceGenerator.createService(IFavoritoService.class);
        final Call<Favorito> call = service.setFavorito(favorito);

        call.enqueue(new Callback<Favorito>() {
            @Override
            public void onResponse(Call<Favorito> call, Response<Favorito> response) {
                Log.v("RESPONSE", response.message());
                if(response.isSuccessful()){
                    Favorito f = response.body();
                    Toast.makeText(AvaliacaoActivity.this, "Favoritado", Toast.LENGTH_SHORT).show();
                    new AtualizaFav().addFavorito(AvaliacaoActivity.this, f);
                    TextView codigofavorito = (TextView) view.findViewById(R.id.a_codigo_favorito);
                    codigofavorito.setText(String.valueOf(f.getCodigo()));
                }
            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                Log.e("ERRO_FAVORITO" , t.getMessage());
            }
        });
    }

    public void desfavoritar(final String codigo){
        IFavoritoService service = ServiceGenerator.createService(IFavoritoService.class);
        final Call<Favorito> call = service.removeFavorito(codigo);

        call.enqueue(new Callback<Favorito>() {
            @Override
            public void onResponse(Call<Favorito> call, Response<Favorito> response) {
                if(response.code() == 410){
                    Toast.makeText(AvaliacaoActivity.this, "Removido", Toast.LENGTH_SHORT).show();
                    Favorito f = new Favorito();
                    f.setCodigo(Integer.parseInt(codigo));
                    new AtualizaFav().removeFavorito(AvaliacaoActivity.this, f);
                }
            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                Log.e("ERRO_desFAVORITO" , t.getMessage());
            }
        });
    }
}
