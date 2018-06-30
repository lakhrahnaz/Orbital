package com.example.user.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {

    private List<Notes> notesList = new ArrayList<> ();
    private NoteAdaptor noteadapter; //spelled adapter properly this time, take note
    private RecyclerView notesListRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        notesListRecycler = (RecyclerView) findViewById(R.id.Notes);
        noteadapter = new NoteAdaptor(notesList);
        RecyclerView.LayoutManager layoutManagement = new LinearLayoutManager(getApplicationContext());
        notesListRecycler.setLayoutManager(layoutManagement);
        notesListRecycler.setItemAnimator(new DefaultItemAnimator());
        notesListRecycler.setAdapter(noteadapter);

        printNotes();

    }

    public void addNotes(View view) {
        Intent intent = new Intent (this, NewNoteActivity.class);
        startActivity(intent);
    }

    private void printNotes() { //prints all the saved notes onto the list notes activity
        File repository;
        repository = getFilesDir();
        File[] files = repository.listFiles();
        String filename;
        String filebody;
        for (int i = 1; i < files.length; i++) {
            filename = files[i].getName();
            filebody = OpenBody(files[i].getName());
            Notes note = new Notes(filename, filebody);
            notesList.add(note);
        }
    }

    public String OpenBody(String name) { //get the content of the note
        String body = "";
        try {
            InputStream input = openFileInput(name);
            if (input != null) {
                InputStreamReader temp = new InputStreamReader( input);
                BufferedReader reader = new BufferedReader(temp);
                String str;
                StringBuilder buffer = new StringBuilder();
                while((str = reader.readLine()) != null) {
                    buffer.append(str + "\n");
                }
                input.close();
                body = buffer.toString();
            }
        }
        catch (java.io.FileNotFoundException e) {
        }
        catch (Throwable t) {
            Toast.makeText(this, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        return body;
    }
}
