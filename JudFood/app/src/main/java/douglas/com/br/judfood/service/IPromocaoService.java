package douglas.com.br.judfood.service;

import java.util.List;

import douglas.com.br.judfood.promocao.Promocao;
import douglas.com.br.judfood.promocao.Promocoes;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Douglas on 02/10/2017.
 */

public interface IPromocaoService {
    @GET("promocao/list")
    Call<List<Promocao>> listPromocoes();
}
