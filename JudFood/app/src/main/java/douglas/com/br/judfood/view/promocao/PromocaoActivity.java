package douglas.com.br.judfood.view.promocao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.promocao.Promocao;
import douglas.com.br.judfood.promocao.Promocoes;
import douglas.com.br.judfood.service.IPromocaoService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.home.HomeActivity;
import douglas.com.br.judfood.view.home.HomeAdapter;
import douglas.com.br.judfood.view.login.LoginActivity;
import douglas.com.br.judfood.view.prato.PratoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromocaoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Promocao> listPromocao = new ArrayList<Promocao>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao);
        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            goLogin();
        }

        listarPromocoes();

    }

    public void voltar(View view){
        Intent intent = new Intent(PromocaoActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarPromocoes(){
        final List<Promocao> promocoes = new ArrayList<Promocao>();
        IPromocaoService service = ServiceGenerator.createService(IPromocaoService.class);
        final Call<List<Promocao>> call = service.listPromocoes();

        call.enqueue(new Callback<List<Promocao>>() {
            @Override
            public void onResponse(Call<List<Promocao>> call, Response<List<Promocao>> response) {
                if(response.isSuccessful()){
                    promocoes.addAll(response.body());
                    recyclerView = (RecyclerView) findViewById(R.id.listaPromocoes);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(PromocaoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new PromocaoAdapter(promocoes, PromocaoActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<Promocao>> call, Throwable t) {
                Log.e("ERRO PROMOCAO",  t.getMessage());
            }
        });
    }
}
