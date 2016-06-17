package br.com.market.infra;

import android.content.Context;
import android.content.SharedPreferences;

public class ParametrosAplicacao {

    private static final String PREFERENCES_NAME = "br.com.market.PREFERENCE_FILE_KEY";

    public static final String CHAVE_FUNCIONARIO_LOGADO = "FUNCIONARIO_LOGADO";

    public static void salvarParametro(Context context, String chave, String valor) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public static String getParametro(Context context, String chave) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String  valor = sharedPref.getString(chave, null);
        return valor;
    }
}
