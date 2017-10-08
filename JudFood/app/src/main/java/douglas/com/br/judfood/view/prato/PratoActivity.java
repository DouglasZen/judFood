package douglas.com.br.judfood.view.prato;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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

import org.w3c.dom.Text;

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
import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
import douglas.com.br.judfood.view.favorito.FavoritoActivity;
import douglas.com.br.judfood.view.home.HomeActivity;
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
            listarPratos(extras.getString("codigo_restaurante").toString());
        }

    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarPratos(String codRestaurante){
        final List<Prato> pratos = new ArrayList<Prato>();

        IPratoService service = ServiceGenerator.createService(IPratoService.class);
        final Call<Pratos> call = service.listPratosRestaurante(Integer.parseInt(codRestaurante));

        call.enqueue(new Callback<Pratos>() {

            @Override
            public void onResponse(Call<Pratos> call, Response<Pratos> response) {
                if (response.isSuccessful()) {
                    Pratos p = response.body();
                    pratos.addAll(p.getPrato());

                    recyclerView = (RecyclerView) findViewById(R.id.listaPratos);
                    RecyclerView.LayoutManager layout =  new GridLayoutManager(PratoActivity.this, 2);
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
                TextView codigo_prato = (TextView) view.findViewById(R.id.codigo_prato);
                Intent i = new Intent(PratoActivity.this, PratoIntegraActivity.class);
                i.putExtra("codigo_prato", codigo_prato.getText().toString());
                startActivity(i);
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
