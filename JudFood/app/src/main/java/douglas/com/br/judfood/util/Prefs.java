package douglas.com.br.judfood.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Douglas on 10/08/2017.
 */

public class Prefs {
    public static final String PREF_ID = "login";

    public static void setLogado (Context context, String chave, boolean on){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(chave, on);
        editor.commit();
    }

    public static boolean getLogado (Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        boolean logado = pref.getBoolean(chave, true);
        return logado;
    }
}
