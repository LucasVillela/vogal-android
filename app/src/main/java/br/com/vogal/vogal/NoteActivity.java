package br.com.vogal.vogal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.com.vogal.adapter.NoteAdapter;
import br.com.vogal.model.Note;
import br.com.vogal.model.Notes;
import br.com.vogal.service.NoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteActivity extends AppCompatActivity {

    GridView gridView;
    NoteAdapter noteAdapter;
    NoteService noteService;
    List<Note> arrayList = new ArrayList<>();
    String notebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent notebookIntent = getIntent();
        this.notebook = notebookIntent.getStringExtra("notebook");

        gridView = (GridView) findViewById(R.id.noteGridView);

        noteService = new NoteService(getApplicationContext());
        noteAdapter = new NoteAdapter(this, arrayList);

        gridView.setAdapter(noteAdapter);


        fillGrid();

    }


    private void fillGrid(){
        Call<Notes> call = noteService.getNotes();
        call.enqueue(handleNotes());
    }





    private Callback<Notes> handleNotes(){
        return new Callback<Notes>() {
            @Override
            public void onResponse(Call<Notes> call, Response<Notes> response) {
                Notes notes = response.body();
                if (notes != null){
                    List<Note> noteList = notes.getNotes();
                    if (noteList.size() > 0){
                        arrayList.clear();
                        for(Note note:noteList){
                            if(note.getNotebook().equals(notebook)){
                                arrayList.add(note);
                            }
                        }

                        noteAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Notes> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_note),"Failed to load notes",Snackbar.LENGTH_SHORT).show();
            }
        };
    }
}
