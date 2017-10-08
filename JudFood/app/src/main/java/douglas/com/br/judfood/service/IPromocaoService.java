package douglas.com.br.judfood.service;

import douglas.com.br.judfood.promocao.Promocoes;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Douglas on 02/10/2017.
 */

public interface IPromocaoService {
    @GET("promocao/list")
    Call<Promocoes> listPromocoes();
}
