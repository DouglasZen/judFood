package douglas.com.br.judfood.service;


import java.util.List;

import douglas.com.br.judfood.pessoa.Pessoa;
import douglas.com.br.judfood.util.Resultado;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Douglas on 08/08/2017.
 */

public interface ILoginService {
    @POST("pessoa/login")
    Call<Pessoa> login(@Body Pessoa pessoa);
}
