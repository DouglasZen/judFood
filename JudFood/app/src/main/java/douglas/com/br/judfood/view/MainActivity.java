package douglas.com.br.judfood.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.prato.Pratos;
import douglas.com.br.judfood.service.IService;
import douglas.com.br.judfood.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listarPratos();
    }

    public void listarPratos(){
        IService service = ServiceGenerator.createService(IService.class);
        final Call<Pratos> call = service.listPratos();

            call.enqueue(new Callback<Pratos>() {
                List<Prato> pratos = new ArrayList<Prato>();
                @Override
                public void onResponse(Call<Pratos> call, Response<Pratos> response) {
                    if (response.isSuccessful()) {

                        Pratos p = response.body();
                        pratos.addAll(p.getPrato());
                        ListView lista = (ListView) findViewById(R.id.listaPratos);
                        ArrayAdapter<Prato> adapter = new ArrayAdapter<Prato>(MainActivity.this, android.R.layout.simple_list_item_1, pratos);
                        lista.setAdapter(adapter);

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
}
