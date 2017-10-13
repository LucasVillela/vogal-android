package br.com.vogal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.vogal.model.Notebook;
import br.com.vogal.vogal.R;

/**
 * Created by lbvil on 12/10/2017.
 */

public class NotebookAdapter extends ArrayAdapter<Notebook>{

    private Context context;
    private List<Notebook> notebookList;


    public NotebookAdapter(Context context, List<Notebook> notebookList){
        super(context, R.layout.row_notebook, notebookList);
        this.context = context;
        this.notebookList = notebookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Notebook notebook = notebookList.get(position);

        NotebookRow notebookRow;

        Integer lastPosition = -1;

        final View result;

        if(convertView != null){

            notebookRow = new NotebookRow();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.row_notebook, parent, false);
            notebookRow.notebookTitle = (TextView) convertView.findViewById(R.id.notebookTitleTextView);
            notebookRow.lastModified = (TextView) convertView.findViewById(R.id.lastModifiedTextView);
            notebookRow.totalNotes = (TextView) convertView.findViewById(R.id.totalNotesTextView);

            result = convertView;

            convertView.setTag(notebookRow);
        }else {
            notebookRow = (NotebookRow) convertView.getTag();
            result = convertView;
        }

        notebookRow.notebookTitle.setText(notebook.getTitle());
        notebookRow.lastModified.setText(notebook.getUpdatedAt().toString());
        notebookRow.totalNotes.setText(new Double(Math.random() * 100).toString());


        return convertView;
    }




    private static class NotebookRow{
        TextView notebookTitle;
        TextView lastModified;
        TextView totalNotes;
    }

}
