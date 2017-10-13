package br.com.vogal.client;

import java.util.List;

import br.com.vogal.model.Login;
import br.com.vogal.model.Note;
import br.com.vogal.model.Notebooks;
import br.com.vogal.model.Notes;
import br.com.vogal.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by lbvil on 11/10/2017.
 */

public interface VogalClient {


    /*
        USUARIO E LOGIN
     */
    @POST("login")
    Call<User> login(@Body Login login);


    /*
        NOTEBOOK
     */
    @GET("notebooks")
    Call<Notebooks> getNotebooks(@Header("Authorization") String authorization);



    /*
        NOTAS
     */
    @GET("notes")
    Call<Notes> getNotes(@Header("Authorization") String authorization);

    @GET("note/{id}")
    Call<Note> getNote(@Header("Authorization") String authorization,
                       @Path("id") String noteId);

    @PUT("note/{id}")
    Call<Note> putNote(@Header("Authorization") String authorization,
                       @Path("id") String noteId,
                       @Body Note note);
}
