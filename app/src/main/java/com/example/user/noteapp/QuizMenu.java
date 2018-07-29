package com.example.user.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class QuizMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
    }

    public void viewQuizMenu(View view) {
        Intent intent = new Intent(this, QuizTitle.class);
        startActivity(intent);
    }
}
