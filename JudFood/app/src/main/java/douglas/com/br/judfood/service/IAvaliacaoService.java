package douglas.com.br.judfood.service;

        import java.util.List;

        import douglas.com.br.judfood.avaliacao.Avaliacao;
        import douglas.com.br.judfood.avaliacao.Avaliacoes;
        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

/**
 * Created by Douglas on 26/09/2017.
 */

public interface IAvaliacaoService {
    @GET("avaliacao/ranking/{codcategoria}")
    Call<List<Avaliacao>> listranking(@Path("codcategoria") int codcategoria);

    @POST("avaliacao/")
    Call<Avaliacao> setAvaliacao(@Body Avaliacao avaliacao);

}
