package douglas.com.br.judfood.service;

import douglas.com.br.judfood.prato.Prato;
import douglas.com.br.judfood.prato.Pratos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Douglas on 01/07/2017.
 */

public interface IPratoService {
    @GET("prato/ranking/{codCategoria}")
    Call<Pratos> listPratos(@Path("codCategoria") int codCategoria);

    @GET("prato/{id}")
    Call<Prato> getPrato(@Path("id") int id);
}
