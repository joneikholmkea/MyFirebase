package com.example.myfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private FirebaseService fs;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fs = new FirebaseService(this);
        adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView, fs.items);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}