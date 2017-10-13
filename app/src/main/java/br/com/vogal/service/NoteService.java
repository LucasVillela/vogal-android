package br.com.vogal.service;

import android.content.Context;

import br.com.vogal.model.Note;
import br.com.vogal.model.Notes;
import retrofit2.Call;

/**
 * Created by lbvil on 13/10/2017.
 */

public class NoteService extends  BaseService{

    private String token;

    public NoteService(Context context){
        super();
        SessionService sessionService = new SessionService(context);
        this.token = sessionService.getToken();
    }

    public Call<Notes> getNotes(){
        return vogalClient.getNotes(token);
    }

    public Call<Note> getNote(String note){
        return vogalClient.getNote(token, note);
    }

    public Call<Note> updateNote(Note note){
        return vogalClient.putNote(token, note.getId(), note);
    }
}
