package douglas.com.br.judfood.view.prato;

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

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.avaliacao.Avaliacao;
import douglas.com.br.judfood.avaliacao.Avaliacoes;
import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.prato.Pratos;
import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.service.IAvaliacaoService;
import douglas.com.br.judfood.service.IFavoritoService;
import douglas.com.br.judfood.service.IPratoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class PratoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Prato> listPrato = new ArrayList<Prato>();
    private boolean cardOpen = false;
    private int originalHeight = 0;
    ImageView iv;
    TextView tvDesc;
    CardView cv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prato);
        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            goLogin();
        }
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            listarPratos(extras.getString("codigoCategoria").toString());
        }

    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarPratos(String codCategoria){
        final List<Prato> pratos = new ArrayList<Prato>();

        IPratoService service = ServiceGenerator.createService(IPratoService.class);

        final Call<Pratos> call = service.listPratos(Integer.parseInt(codCategoria));

        call.enqueue(new Callback<Pratos>() {

            @Override
            public void onResponse(Call<Pratos> call, Response<Pratos> response) {
                if (response.isSuccessful()) {
                    Pratos p = response.body();
                    pratos.addAll(p.getPrato());

                    recyclerView = (RecyclerView) findViewById(R.id.listaPratos);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(PratoActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new PratoAdapter(pratos, PratoActivity.this, onClickPrato()));
                    listPrato = pratos;

                } else {
                    Log.e("ERRO RESPONSE",  response.message());

                }


            }

            @Override
            public void onFailure(Call<Pratos> call, Throwable t) {
                t.printStackTrace();
                Log.e("ERRO",  t.getMessage());
            }
        });


    }



    private PratoAdapter.PratoOnClickListener onClickPrato(){
        return new PratoAdapter.PratoOnClickListener(){
            @Override
            public void onClickPrato(final View view, int idx){
                ValueAnimator valueAnimator;
                iv = (ImageView) view.findViewById(R.id.imageView);
                tvDesc = (TextView) view.findViewById(R.id.tdescricao);
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
                    cv = (CardView) view.findViewById(R.id.cardPrato);
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
                ImageButton star = (ImageButton)  view.findViewById(R.id.favorito);
                TextView codigo = (TextView) view.findViewById(R.id.codigo_prato);
                TextView codigoRestaurante = (TextView) view.findViewById(R.id.codigo_restaurante);
                star.setColorFilter(Color.rgb(255,255,0));
                int codigoPessoa = Prefs.getCodigoPessoa(PratoActivity.this,"codigoPessoa");
                Toast.makeText(PratoActivity.this, codigo.getText() + " rest " + codigoRestaurante.getText() , Toast.LENGTH_SHORT).show();
                favoritar(codigoPessoa, Integer.parseInt(codigo.getText().toString()), Integer.parseInt(codigoRestaurante.getText().toString()));
            }
        };
    }

    public void favoritar(int codigoPessoa, int codigoPrato, int codigoRestaurante){
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
                    Toast.makeText(PratoActivity.this, "Favoritado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                Log.e("ERRO_FAVORITO" , t.getMessage());
            }
        });
    }
}
