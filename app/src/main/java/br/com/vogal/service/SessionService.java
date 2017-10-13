package br.com.vogal.service;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.vogal.model.User;

/**
 * Created by lbvil on 12/10/2017.
 */

public class SessionService {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "SESSION";

    public SessionService(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        this.editor = this.sharedPreferences.edit();
    }


    public void createSession(User user){
        editor.putString("username",user.getUsername());
        editor.putString("token",user.getUsername());
        editor.putString("profile_pic",user.getProfilePic());
        editor.putString("email",user.getEmail());

        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString("token",null);
    }

    public User getUser(){
        User user = new User();

        user.setToken(sharedPreferences.getString("token",null));
        user.setUsername(sharedPreferences.getString("username",null));
        user.setProfilePic(sharedPreferences.getString("profile_pic",null));
        user.setEmail(sharedPreferences.getString("email",null));

        return user;
    }

    public void finishSession(){
        editor.clear();
        editor.commit();
    }
}
