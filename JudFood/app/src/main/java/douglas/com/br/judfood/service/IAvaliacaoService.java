package douglas.com.br.judfood.service;

        import douglas.com.br.judfood.avaliacao.Avaliacao;
        import douglas.com.br.judfood.avaliacao.Avaliacoes;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;

/**
 * Created by Douglas on 26/09/2017.
 */

public interface IAvaliacaoService {
    @GET("avaliacao/ranking/{codcategoria}")
    Call<Avaliacoes> listranking(@Path("codcategoria") int codcategoria);

}
