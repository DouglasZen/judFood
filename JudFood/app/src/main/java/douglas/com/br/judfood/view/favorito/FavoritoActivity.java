package douglas.com.br.judfood.view.favorito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.favorito.Favoritos;
import douglas.com.br.judfood.service.IFavoritoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Favorito> listFavorito = new ArrayList<Favorito>();
    ImageView iv;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);
        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            goLogin();
        }
        listarFavoritos();

    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarFavoritos(){
        int codigoPessoa = Prefs.getCodigoPessoa(FavoritoActivity.this,"codigoPessoa");
        final List<Favorito> favoritos = new ArrayList<Favorito>();

        IFavoritoService service = ServiceGenerator.createService(IFavoritoService.class);
        final Call<Favoritos> call = service.listFavoritoPessoa(codigoPessoa);

        call.enqueue(new Callback<Favoritos>() {
            @Override
            public void onResponse(Call<Favoritos> call, Response<Favoritos> response) {
                if(response.isSuccessful()){
                    Favoritos f = response.body();
                    favoritos.addAll(f.getFavorito());

                    recyclerView = (RecyclerView) findViewById(R.id.rlistaFavoritos);
                    RecyclerView.LayoutManager layout =  new GridLayoutManager(FavoritoActivity.this, 2); // new LinearLayoutManager(FavoritoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new FavoritoAdapter(favoritos, FavoritoActivity.this, onClickFavorito()));
                    listFavorito = favoritos;
                }
            }

            @Override
            public void onFailure(Call<Favoritos> call, Throwable t) {
                Log.e("FAV_ERRO",  t.getMessage());
            }
        });
    }

    private FavoritoAdapter.FavoritoOnClickListener onClickFavorito(){
        return new FavoritoAdapter.FavoritoOnClickListener(){

            @Override
            public void onClickFavoritar(View view, int idx) {

            }

            @Override
            public void onClickIntegra(View view, int idx) {

            }
        };
    }
}
