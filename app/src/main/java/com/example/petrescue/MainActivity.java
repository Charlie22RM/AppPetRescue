package com.example.petrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

        Button btn_adopcion = findViewById(R.id.btn_adopcion);
        Button btn_adoptar = findViewById(R.id.btn_adoptar);

        btn_adopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainAdopcionActivity.class);
                startActivity(intent);
            }
        });
        btn_adoptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainAdoptarActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Verificar qué opción del menú se seleccionó
        int id = item.getItemId();
        if (id == R.id.opc1) {
            // Acción para la opción "Adoptar"
            Intent adoptarIntent = new Intent(MainActivity.this, MainAdoptarActivity.class);
            startActivity(adoptarIntent);
            return true;
        } else if (id == R.id.opc2) {
            // Acción para la opción "Dar en adopción"
            Intent darEnAdopcionIntent = new Intent(MainActivity.this, MainAdopcionActivity.class);
            startActivity(darEnAdopcionIntent);
            return true;
        } else if (id == R.id.opc3) {
            // Acción para la opción "Cerrar sesión"
            Intent salirIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(salirIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}