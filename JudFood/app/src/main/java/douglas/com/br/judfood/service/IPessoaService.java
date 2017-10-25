package douglas.com.br.judfood.service;

import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.util.Resultado;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Douglas on 29/07/2017.
 */

public interface IPessoaService {
    @POST("pessoa/")
    Call<Pessoa> setPessoa(@Body Pessoa pessoa);

    @GET("pessoa/facebook/{id}")
    Call<Pessoa> getPessoaFacebook(@Path("id") String id);

    @POST("pessoa/verificaemail")
    Call<Resultado> verificaemail(@Body Pessoa pessoa);
}
