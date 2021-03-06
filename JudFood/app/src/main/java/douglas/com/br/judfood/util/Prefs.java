package douglas.com.br.judfood.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

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

    public static void setCodigoPessoa(Context context, String chave, int valor){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(chave, valor);
        editor.commit();

    }

    public static int getCodigoPessoa(Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        int s = pref.getInt(chave, 0);
        return  s;
    }

    public static void setFavoritos(Context context, String chave, String lista){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(chave, lista);
        editor.commit();
    }

    public static String getFavoritos(Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        String s = pref.getString(chave, "");
        return  s;
    }
}
