package douglas.com.br.judfood.view.home;

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
import douglas.com.br.judfood.categoria.Categoria;
import douglas.com.br.judfood.categoria.Categorias;
import douglas.com.br.judfood.service.ICategoriaService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.MainActivity;
import douglas.com.br.judfood.view.avaliacao.AvaliacaoActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import douglas.com.br.judfood.view.prato.PratoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        boolean logado = Prefs.getLogado(this, "login");
        if(AccessToken.getCurrentAccessToken() == null && !logado){
            Log.v("MAIN", "sucesso");
            goLogin();
        }
        listarCategorias();
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void listarCategorias(){
        final List<Categoria> categorias = new ArrayList<Categoria>();
        ICategoriaService service = ServiceGenerator.createService(ICategoriaService.class);
        final Call<Categorias> call = service.listCategoria();

        call.enqueue(new Callback<Categorias>() {
            @Override
            public void onResponse(Call<Categorias> call, Response<Categorias> response) {
                if(response.isSuccessful()){
                    Categorias c = response.body();
                    categorias.addAll(c.getCategoria());
                    recyclerView = (RecyclerView) findViewById(R.id.listaCategorias);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(new HomeAdapter(categorias, HomeActivity.this, onClickCategoria()));
                }
            }

            @Override
            public void onFailure(Call<Categorias> call, Throwable t) {
                Log.e("ERRO HOME",  t.getMessage());
            }
        });
    }

    private HomeAdapter.CategoriaOnClickListener onClickCategoria(){
        return new HomeAdapter.CategoriaOnClickListener() {
            @Override
            public void onClickCategoria(View view, int idx) {
                TextView codigo = (TextView) view.findViewById(R.id.tcodigo);
                Intent i = new Intent(HomeActivity.this, AvaliacaoActivity.class);
                i.putExtra("codigoCategoria", codigo.getText().toString());
                startActivity(i);
            }
        };

    }
}
