package br.com.market.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsavel pela aplicacao
 * @author TIVIT
 * @since 4/6/16.
 */
public class TSCApplication extends Application {

    private static TSCApplication instance;

    private SharedPreferences preferences;
    private List<AsyncTask<?,?,?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();

    public static TSCApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //Cancela tarefas
        for(AsyncTask task : tasks) {
            task.cancel(true);
        }
    }

    public void registra(AsyncTask<?,?,?> task) {
        Log.i("Registra", "Reginstrando AsyncTask: " + task);
        tasks.add(task);
    }

    public void desregistra(AsyncTask<?,?,?> task) {
        Log.i("Desregistra", "Desreginstrando AsyncTask: " + task);
        tasks.remove(task);
    }

    public void putBooleanSharedValue(String key, Boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBooleanSharedValue(String key) {
        return preferences.getBoolean(key, Boolean.FALSE);
    }

    /*public String getAccessToken() {
        return UsuarioController.getInstance().getAccessToken();
    }*/
}
