package com.example.user.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
    }

    public void addNotes(View view) {
        Intent intent = new Intent (this, NewNoteActivity.class);
        startActivity(intent);
    }
}
