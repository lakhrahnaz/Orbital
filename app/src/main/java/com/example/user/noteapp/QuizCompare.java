package com.example.user.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizCompare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_compare);

        Intent intent = getIntent();
        String name = intent.getStringExtra(QuizTime.EXTRA_NAME);
        TextView textView1 = findViewById(R.id.Name);
        textView1.setText(name);

        String body = intent.getStringExtra(QuizTime.EXTRA_BODY);
        TextView textView2 = findViewById(R.id.Body);
        textView2.setText(body);

        String answer = intent.getStringExtra(QuizTime.EXTRA_ANSWER);
        TextView textView3 = findViewById(R.id.Answer);
        textView3.setText(answer);
    }
}
