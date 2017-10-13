package br.com.vogal.service;

import java.io.IOException;

import br.com.vogal.client.VogalClient;
import br.com.vogal.model.Login;
import br.com.vogal.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lbvil on 12/10/2017.
 */

public class AuthService extends BaseService {

    public AuthService(){
        super();
    }

    public Call<User> login(String username, String password){
        return vogalClient.login(new Login(username,password));
    }




}
