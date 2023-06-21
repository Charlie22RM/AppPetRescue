package com.example.petrescue;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainAdoptarActivity extends AppCompatActivity {

    private Toolbar toolbar_adoptar;

    private ListView listView_perros;
    private PerroAdapter perroAdapter;
    private List<Perro> perrosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adoptar);
        toolbar_adoptar = findViewById(R.id.toolbar_adoptar);
        setSupportActionBar(toolbar_adoptar);

        listView_perros = findViewById(R.id.listView_perros);
        perrosList = new ArrayList<>();
        perroAdapter = new PerroAdapter(this, R.layout.item_perro, perrosList);
        listView_perros.setAdapter(perroAdapter);

        listView_perros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perro perro = perrosList.get(position);
                Intent intent = new Intent(MainAdoptarActivity.this, InfoPerrosActivity.class);
                intent.putExtra("perro_id", perro.getId());
                startActivity(intent);
            }
        });

        cargarPerrosDesdeBD();
    }

    private void cargarPerrosDesdeBD() {
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (db != null) {
            String[] projection = {"id", "nombre", "raza", "imagen_path"};
            Cursor cursor = db.query("dogs", projection, null, null, null, null, null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String raza = cursor.getString(cursor.getColumnIndexOrThrow("raza"));
                String imagenPath = cursor.getString(cursor.getColumnIndexOrThrow("imagen_path"));

                Perro perro = new Perro(id, nombre, raza, imagenPath);
                perrosList.add(perro);
            }

            cursor.close();
            db.close();
        }

        perroAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}