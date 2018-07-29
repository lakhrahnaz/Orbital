package com.example.user.noteapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizTitle extends AppCompatActivity {

    private List<Notes> notesList = new ArrayList<> ();
    private QuizAdaptor noteadapter; //spelled adapter properly this time, take note
    private RecyclerView notesListRecycler;
    public static final String EXTRA_MESSAGE = "com.example.user.noteapp.MESSAGE";
    public static final String EXTRA_NAME = "com.example.user.noteapp.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_title);
        notesListRecycler = (RecyclerView) findViewById(R.id.NoteTitles);
        noteadapter = new QuizAdaptor(notesList);
        RecyclerView.LayoutManager layoutManagement = new LinearLayoutManager(getApplicationContext());
        notesListRecycler.setLayoutManager(layoutManagement);
        notesListRecycler.setItemAnimator(new DefaultItemAnimator());
        notesListRecycler.setAdapter(noteadapter);
        notesListRecycler.addOnItemTouchListener(new RecyclerTouchListenerQ(this, notesListRecycler, new QuizTitle.ClickListenerQ() {
            @Override
            public void onClick(android.view.View view, final int position) {
                Intent intent = new Intent (view.getContext(), QuizTime.class);
                intent.putExtra(EXTRA_MESSAGE, notesList.get(position).getBody());
                intent.putExtra(EXTRA_NAME, notesList.get(position).getTitle());
                startActivity(intent);
            }
        }));

        printNotes();

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
    public static interface ClickListenerQ {
        public void onClick(View view, int position);
    }
    class RecyclerTouchListenerQ implements RecyclerView.OnItemTouchListener{
        private ClickListenerQ clicklistenerq;
        private GestureDetector gestureDetector;

        public RecyclerTouchListenerQ(Context context, final RecyclerView recycleView, final ClickListenerQ clicklistenerq) {
            this.clicklistenerq = clicklistenerq;
            gestureDetector = new GestureDetector (context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child!=null && clicklistenerq!=null && gestureDetector.onTouchEvent (e)) {
                clicklistenerq.onClick(child, rv.getChildAdapterPosition(child));
            }
                return false;

        }
        @Override
        public void onTouchEvent (RecyclerView rv, MotionEvent e){

        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){

        }
    }



}
