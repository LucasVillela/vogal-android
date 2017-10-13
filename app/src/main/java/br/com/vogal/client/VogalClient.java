package br.com.vogal.client;

import java.util.List;

import br.com.vogal.model.Login;
import br.com.vogal.model.Notebooks;
import br.com.vogal.model.Notes;
import br.com.vogal.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by lbvil on 11/10/2017.
 */

public interface VogalClient {

    @POST("login")
    Call<User> login(@Body Login login);

    @GET("notebooks")
    Call<Notebooks> getNotebooks(@Header("Authorization") String authorization);

    @GET("notes")
    Call<Notes> getNotes(@Header("Authorization") String authorization);
}
