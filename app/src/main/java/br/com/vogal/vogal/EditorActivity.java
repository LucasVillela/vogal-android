package br.com.vogal.vogal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.vogal.model.Note;
import br.com.vogal.service.NoteService;
import jp.wasabeef.richeditor.RichEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {


    private RichEditor mEditor;
    String noteId;
    NoteService noteService;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent note = getIntent();
        noteId = note.getStringExtra("note");

        noteService = new NoteService(getApplicationContext());

        Call<Note> call = noteService.getNote(noteId);
        call.enqueue(handleGetNote());

    }


    private void mountEditor(){
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorFontSize(22);
        mEditor.setPlaceholder("Insert text here...");

        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                note.setTextHTML(text);
                Call<Note> call = noteService.updateNote(note);
                call.enqueue(handleUpdateNote());
            }
        });


        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });
    }

    private Callback<Note> handleUpdateNote(){
        return new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                Snackbar.make(findViewById(R.id.activity_editor),"Saved",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_editor),"Failed to save",Snackbar.LENGTH_SHORT).show();
            }
        };
    }

    private Callback<Note> handleGetNote(){
        return new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if(response.body() != null){
                    note = response.body();
                    mountEditor();
                    mEditor.setHtml(note.getTextHTML());

                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_editor),"Failed to load Note",Snackbar.LENGTH_SHORT).show();
            }
        };
    }


}
