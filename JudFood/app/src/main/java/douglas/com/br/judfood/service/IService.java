package douglas.com.br.judfood.service;

import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.prato.Pratos;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Douglas on 05/06/2017.
 */

public interface IService {
    @GET("teste/")
    Call<Pratos> listPratos();
}
