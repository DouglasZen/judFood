package douglas.com.br.judfood.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import douglas.com.br.judfood.favorito.Favorito;


/**
 * Created by Douglas on 24/10/2017.
 */

public class AtualizaFav {
    public void addFavorito(Context context, Favorito favorito){
        String f = Prefs.getFavoritos(context, "favoritos");
        Type listType = new TypeToken<List<Favorito>>(){}.getType();
        List<Favorito> favoritos = (List<Favorito>) new Gson().fromJson(f, listType);
        favoritos.add(favorito);
        Prefs.setFavoritos(context, "favoritos", new Gson().toJson(favoritos));
    }

    public void removeFavorito(Context context, Favorito favorito){
        String f = Prefs.getFavoritos(context, "favoritos");
        Type listType = new TypeToken<List<Favorito>>(){}.getType();
        List<Favorito> favoritos = (List<Favorito>) new Gson().fromJson(f, listType);
        List<Favorito> faux = new ArrayList<Favorito>();
        faux.addAll(favoritos);
        for (Favorito fav: faux) {
            if(fav.getCodigo() == favorito.getCodigo()){
                favoritos.remove(fav);
            }
        }
        Prefs.setFavoritos(context, "favoritos", new Gson().toJson(favoritos));
    }
}
