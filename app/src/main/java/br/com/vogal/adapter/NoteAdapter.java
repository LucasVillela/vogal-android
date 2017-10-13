package br.com.vogal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.vogal.model.Note;
import br.com.vogal.vogal.R;

/**
 * Created by lbvil on 13/10/2017.
 */

public class NoteAdapter extends BaseAdapter {

    List<Note> noteList;
    Context context;

    public NoteAdapter(Context context, List<Note> noteList){
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View grid = inflater.inflate(R.layout.grid_note, parent, false);
        TextView title = (TextView) grid.findViewById(R.id.titleTextView);
        TextView noteText = (TextView) grid.findViewById(R.id.noteTextView);

        Note note = noteList.get(position);

        //TODO SETAR VALORES NAS TEXTS VIEWS
        //title.setText();
        //noteText.setText();

        return grid;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
