package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref = null;
    public static final String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("My Preferences", MODE_PRIVATE);

        final ArrayList<UserNote> userNotes = new ArrayList<>();
        final NotesAdapter notesAdapter = new NotesAdapter(userNotes);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(notesAdapter);

        String savedNotes = sharedPref.getString(KEY, null);
        if (savedNotes == null || savedNotes.isEmpty()) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Type type = new TypeToken<ArrayList<UserNote>>(){}.getType();
                ArrayList<UserNote> listOfNotes = new GsonBuilder().create().fromJson(savedNotes, type);
                for (UserNote oneNote: listOfNotes) {
                    userNotes.add(oneNote);
                }
                notesAdapter.setNewData(listOfNotes);
            } catch (JsonSyntaxException e) {
                Toast.makeText(this, "Ошибка трансформации", Toast.LENGTH_SHORT).show();
            }
        }

        findViewById(R.id.fab).setOnClickListener(view -> {
            userNotes.add(new UserNote("New note", new Date(), "New note"));
            notesAdapter.setNewData(userNotes);
            String jsonNotes = new GsonBuilder().create().toJson(userNotes);
            sharedPref.edit().putString(KEY, jsonNotes).apply();
        });
    }
}
