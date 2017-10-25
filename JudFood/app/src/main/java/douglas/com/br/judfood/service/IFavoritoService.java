package douglas.com.br.judfood.service;

import java.util.List;

import douglas.com.br.judfood.favorito.Favorito;
import douglas.com.br.judfood.favorito.Favoritos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Douglas on 28/08/2017.
 */

public interface IFavoritoService {
    @POST("favorito/")
    Call<Favorito> setFavorito(@Body Favorito favorito);

    @DELETE("favorito/{id}")
    Call<Favorito> removeFavorito(@Path("id") String id);

    @GET("favorito/lista/{codigopessoa}")
    Call<List<Favorito>> listFavoritoPessoa(@Path("codigopessoa") int codigopessoa);

    @GET("favorito/favoritos/{codigopessoa}")
    Call<List<Favorito>> codigos(@Path("codigopessoa") int codigopessoa);
}
