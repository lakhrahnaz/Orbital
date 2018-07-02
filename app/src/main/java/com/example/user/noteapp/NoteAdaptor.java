package com.example.user.noteapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteAdaptor extends RecyclerView.Adapter <NoteAdaptor.MyViewHolder> { //I spelled adapter wrongly so i guess thats it
    private List <Notes> notesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            body = (TextView) view.findViewById(R.id.body);
        }
    }

    public NoteAdaptor(List <Notes> notesList) {
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notes note = notesList.get(position);
        holder.title.setText(note.getTitle());
        //holder.body.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
