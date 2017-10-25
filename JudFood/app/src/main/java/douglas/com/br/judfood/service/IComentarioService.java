package douglas.com.br.judfood.service;


import java.util.List;

import douglas.com.br.judfood.comentario.Comentario;
import douglas.com.br.judfood.comentario.Comentarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Douglas on 08/10/2017.
 */

public interface IComentarioService {
    @POST("comentario/")
    Call<Comentario> setComentario(@Body Comentario comentario);

    @POST("comentario/setResposta")
    Call<Comentario> setResposta(@Body Comentario resposta);

    @GET("comentario/lista/{codigoprato}/{max}")
    Call<List<Comentario>> listaComentarios (@Path("codigoprato") int codigoPrato, @Path("max") int max);

    @GET("comentario/respostas/{codigocomentario}")
    Call<Comentario> getComentario(@Path("codigocomentario") int codigoComentario);
}
