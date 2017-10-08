package douglas.com.br.judfood.view.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.restaurante.Restaurantes;
import douglas.com.br.judfood.service.IRestauranteService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
import douglas.com.br.judfood.view.home.HomeActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import douglas.com.br.judfood.view.prato.PratoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestauranteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Restaurante> listRestaurante = new ArrayList<Restaurante>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            goLogin();
        }
        listarRestaurante();

    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void listarRestaurante(){
        final List<Restaurante> restaurantes = new ArrayList<Restaurante>();

        IRestauranteService service = ServiceGenerator.createService(IRestauranteService.class);
        final Call<Restaurantes> call = service.listaRestaurantes();

        call.enqueue(new Callback<Restaurantes>() {
            @Override
            public void onResponse(Call<Restaurantes> call, Response<Restaurantes> response) {
                if(response.isSuccessful()){
                    Restaurantes r = response.body();

                    restaurantes.addAll(r.getRestaurante());
                    recyclerView = (RecyclerView) findViewById(R.id.listaRestaurantes);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(RestauranteActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new RestauranteAdapter(restaurantes, RestauranteActivity.this, onClickRestaurante()));

                }else {
                    Log.e("ERRO RESPONSE",  response.message());
                }
            }

            @Override
            public void onFailure(Call<Restaurantes> call, Throwable t) {
                Log.e("ERRO",  t.getMessage());
            }
        });
    }

    public RestauranteAdapter.RestauranteOnClickListener onClickRestaurante(){
        return new RestauranteAdapter.RestauranteOnClickListener(){

            @Override
            public void onClickRestaurante(View view, int idx) {
                TextView codigo = (TextView) view.findViewById(R.id.restaurante_codigo);
                Intent i = new Intent(RestauranteActivity.this, PratoActivity.class);
                i.putExtra("codigo_restaurante", codigo.getText().toString());
                startActivity(i);
            }
        };
    }

}
