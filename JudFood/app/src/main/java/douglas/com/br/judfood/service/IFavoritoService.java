package douglas.com.br.judfood.service;

import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.favorito.Favoritos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Douglas on 28/08/2017.
 */

public interface IFavoritoService {
    @POST("favorito/")
    Call<Favorito> setFavorito(@Body Favorito favorito);

    @GET("favorito/lista/{codigopessoa}")
    Call<Favoritos> listFavoritoPessoa(@Path("codigopessoa") int codigopessoa);
}
