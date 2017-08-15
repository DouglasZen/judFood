package douglas.com.br.judfood.view.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.service.IPessoaService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.view.MainActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText senha;
    EditText confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void cadastrar(View view){
        nome = (EditText) findViewById(R.id.etNome);
        email = (EditText) findViewById(R.id.etEmail);
        senha = (EditText) findViewById(R.id.etSenha);
        confSenha = (EditText) findViewById(R.id.etConfSenha);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome.getText().toString());
        pessoa.setEmail(email.getText().toString());
        pessoa.setSenha(senha.getText().toString());

        setPessoa(pessoa);


    }

    public void setPessoa(Pessoa pessoa){
        IPessoaService service = ServiceGenerator.createService(IPessoaService.class);
        final Call<Pessoa> call = service.setPessoa(pessoa);

        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                Log.v("RESPONSE", response.message());
                if(response.isSuccessful()){
                    Intent intent = new Intent(CadastroActivity.this, CadastroSucessoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.v("JSON",  t.getMessage());
            }
        });
    }
}
