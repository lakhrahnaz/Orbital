package com.example.user.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class NewNoteActivity extends AppCompatActivity {

    EditText Name;
    EditText Body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }
    public void onSaveClick (View view){
        Body = (EditText) findViewById(R.id.editText);
        Name = (EditText) findViewById(R.id.editText2);
        Save(Name);

    }
    public void Save (EditText title) {
        try {
            OutputStreamWriter output = new OutputStreamWriter(openFileOutput(title.getText().toString(), 0));
            output.write(Body.getText().toString());
            output.close();
            Toast.makeText(this, "Save Complete", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }


}
