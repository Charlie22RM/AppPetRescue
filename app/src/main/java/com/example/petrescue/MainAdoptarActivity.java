package com.example.petrescue;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainAdoptarActivity extends AppCompatActivity {

    private Toolbar toolbar_adoptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adoptar);
        toolbar_adoptar = findViewById(R.id.toolbar_adoptar);
        setSupportActionBar(toolbar_adoptar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }
}