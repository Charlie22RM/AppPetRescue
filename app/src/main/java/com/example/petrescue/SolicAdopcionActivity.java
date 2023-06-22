package com.example.petrescue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SolicAdopcionActivity extends AppCompatActivity {

    private Toolbar toolbar_solic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solic_adopcion);
        toolbar_solic = findViewById(R.id.toolbar_solic);
        setSupportActionBar(toolbar_solic);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }
}