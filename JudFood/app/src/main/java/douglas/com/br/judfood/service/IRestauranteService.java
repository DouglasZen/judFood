package douglas.com.br.judfood.service;

import java.util.List;

import douglas.com.br.judfood.restaurante.Restaurante;
import douglas.com.br.judfood.restaurante.Restaurantes;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Douglas on 28/09/2017.
 */

public interface IRestauranteService {
    @GET("restaurante/lista")
    Call<List<Restaurante>> listaRestaurantes();
}
