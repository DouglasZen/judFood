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
import douglas.com.br.judfood.util.Resultado;
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

        if(senha.getText().toString().equals(confSenha.getText().toString())) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setEmail(email.getText().toString());
            pessoa.setSenha(senha.getText().toString());
            verificaEmail(pessoa);
        }else{
            Toast.makeText(CadastroActivity.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
        }


    }

    public void verificaEmail(final Pessoa pessoa){
        IPessoaService service = ServiceGenerator.createService(IPessoaService.class);
        final Call<Resultado> call = service.verificaemail(pessoa);

        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if(response.isSuccessful()){
                    Resultado resultado = response.body();
                    if(!resultado.isResultado()) {
                        setPessoa(pessoa);
                    }else {
                        Toast.makeText(CadastroActivity.this, "Esse e-mail ja está vinculado", Toast.LENGTH_LONG).show();
                    }

                }
                Log.v("RESPONSE", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.e("RESPONSE ERROR", t.getMessage());
            }
        });

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
