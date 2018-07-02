package com.example.user.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class NoteView extends AppCompatActivity {
    public String name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        Intent intent = getIntent();
        String body = intent.getStringExtra(ListNotesActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(body);
        name = intent.getStringExtra(ListNotesActivity.EXTRA_NAME);
    }

    public void deleteNote(View view) {
        /*File files = getFileStreamPath(name);
        files.delete();*/
        File dir = getFilesDir();
        File file = new File(dir, name);
        boolean deleted = file.delete();
        Toast.makeText(this, "Note Deleted.", Toast.LENGTH_SHORT).show();

        }
}
