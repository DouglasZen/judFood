package douglas.com.br.judfood.view.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.service.ILoginService;
import douglas.com.br.judfood.service.IPessoaService;
import douglas.com.br.judfood.service.ServiceGenerator;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.util.Resultado;
import douglas.com.br.judfood.view.MainActivity;
import douglas.com.br.judfood.view.cadastro.CadastroActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                callbackManager = CallbackManager.Factory.create();
                loginButton = (LoginButton) findViewById(R.id.faceBut);
                loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {


                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.v("JSON",  object.toString());
                                    Pessoa p = new Pessoa();
                                    p.setEmail(object.getString("email"));
                                    p.setNome(object.getString("name"));
                                    p.setIdFacebook(object.getString("id"));
                                    verificaPessoaCadastrada(p);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "login cancelado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("erro", error.getMessage());


                    }
                });
            }
        });
    }

    public void setPessoaFacebook(Pessoa pessoa){
        IPessoaService service = ServiceGenerator.createService(IPessoaService.class);
        final Call<Pessoa> call = service.setPessoa(pessoa);

        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                Log.v("RESPONSE", response.message());
                if(response.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.v("JSON",  t.getMessage());
                Toast.makeText(LoginActivity.this, "Erro !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verificaPessoaCadastrada(final Pessoa pessoa){
        IPessoaService service = ServiceGenerator.createService(IPessoaService.class);
        final Call<Pessoa> call = service.getPessoaFacebook(pessoa.getIdFacebook());
        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.code() != 404){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    setPessoaFacebook(pessoa);
                }

            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.e("FALHOU", t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void cadastrar(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void entrar(View view){
        Prefs.setLogado(this, "login", false);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        Pessoa pessoa = new Pessoa();
        pessoa.setSenha(senha.getText().toString());
        pessoa.setEmail(email.getText().toString());

        ILoginService service = ServiceGenerator.createService(ILoginService.class);
        final Call<Resultado> call = service.login(pessoa);
        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if(response.isSuccessful()){
                    Resultado r = response.body();
                    if(r.isResultado()){
                        Log.v("LOGIN", "logou");
                        Prefs.setLogado(LoginActivity.this, "login", true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.e("ERRO LOGIN", t.getMessage());
            }
        });
    }
}
