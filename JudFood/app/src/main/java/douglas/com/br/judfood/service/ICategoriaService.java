package douglas.com.br.judfood.service;

import java.util.List;

import douglas.com.br.judfood.categoria.Categoria;
import douglas.com.br.judfood.categoria.Categorias;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Douglas on 16/08/2017.
 */

public interface ICategoriaService {
    @GET("categoria/")
    Call<List<Categoria>> listCategoria();
}
