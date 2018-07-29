package com.example.user.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class QuizTime extends AppCompatActivity {
    public String body = "body";
    public String name = "name";
    public String answer = "answer";
    public static final String EXTRA_BODY = "com.example.user.noteapp.BODY";
    public static final String EXTRA_NAME = "com.example.user.noteapp.NAME";
    public static final String EXTRA_ANSWER = "com.example.user.noteapp.ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_time);

        Intent intent = getIntent();
        body = intent.getStringExtra(QuizTitle.EXTRA_MESSAGE);
        name = intent.getStringExtra(QuizTitle.EXTRA_NAME);
    }

    public void submitAnswer(View view) {
        Intent intent = new Intent(this, QuizCompare.class);
        EditText editText = (EditText) findViewById(R.id.answerBox);
        answer = editText.getText().toString();
        intent.putExtra(EXTRA_BODY, body);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_ANSWER, answer);
        startActivity(intent);
    }
}
