package com.example.twitter3.helper;
import android.content.Context;
import android.content.SharedPreferences;


public class MyPreferenceManager {

    /**
     * Variables de preferencias
     */
    private static final String PREF_KEY = "login_prefs";
    private static final String USER_ID = "user_id";
    private static final String SCREEN_NAME = "screen_name";

    //Instancia de PreferenceManager
    private SharedPreferences sharedPreferences;

    public MyPreferenceManager(Context context) {
        //Se inicializa la preferencia
        sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Método para guardar el id del usuario
     *
     * parametro userId para guardar
     */
    public void saveUserId(Long userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(USER_ID, userId);
        editor.apply();
    }

    /**
     * Devuelve el id de usuario si existe, si no retorna 0
     */
    public Long getUserId() {
        return sharedPreferences.getLong(USER_ID, 0);
    }

    /**
     * method to save user screen name
     *Método para guardar el nombre de usuario que aparece en pantalla (screenName)
     */
    public void saveScreenName(String screenName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SCREEN_NAME, screenName);
        editor.apply();
    }

    /**
     * Nos devuelve el nombre de pantalla del usuario
     */
    public String getScreenName() {
        return sharedPreferences.getString(SCREEN_NAME, "");
    }
}


