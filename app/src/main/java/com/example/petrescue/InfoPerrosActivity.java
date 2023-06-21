package com.example.petrescue;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class InfoPerrosActivity extends AppCompatActivity {

    private Toolbar toolbar_info;
    private TextView nombreTextView;
    private TextView edadTextView;
    private TextView razaTextView;
    private TextView tamañoTextView;
    private TextView generoTextView;
    private TextView vac1TextView;
    private TextView vac2TextView;
    private TextView vac3TextView;
    private TextView vac4TextView;

    private ImageView imageViewPerro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_perros);
        toolbar_info = findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar_info);

        nombreTextView = findViewById(R.id.textView_nb);
        edadTextView = findViewById(R.id.textView_edad);
        razaTextView = findViewById(R.id.textView_raza);
        tamañoTextView = findViewById(R.id.textView_tamano);
        generoTextView = findViewById(R.id.textView_genero);
        vac1TextView = findViewById(R.id.textView_vac1);
        vac2TextView = findViewById(R.id.textView_vac2);
        vac3TextView = findViewById(R.id.textView_vac3);
        vac4TextView = findViewById(R.id.textView_vac4);
        imageViewPerro = findViewById(R.id.img_perro);

        int perroId = getIntent().getIntExtra("perro_id", -1);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (db != null) {
            String[] projection = {"nombre", "edad", "raza", "tamaño", "genero", "vac1", "vac2", "vac3", "vac4", "imagen_path"};
            String selection = "id = ?";
            String[] selectionArgs = {String.valueOf(perroId)};
            Cursor cursor = db.query("dogs", projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String edad = cursor.getString(cursor.getColumnIndexOrThrow("edad"));
                String raza = cursor.getString(cursor.getColumnIndexOrThrow("raza"));
                String tamaño = cursor.getString(cursor.getColumnIndexOrThrow("tamaño"));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow("genero"));
                String vac1 = cursor.getString(cursor.getColumnIndexOrThrow("vac1"));
                String vac2 = cursor.getString(cursor.getColumnIndexOrThrow("vac2"));
                String vac3 = cursor.getString(cursor.getColumnIndexOrThrow("vac3"));
                String vac4 = cursor.getString(cursor.getColumnIndexOrThrow("vac4"));

                String imagenPath = cursor.getString(cursor.getColumnIndexOrThrow("imagen_path"));

                // Cargar la imagen utilizando Picasso
                Picasso.get().load(imagenPath).placeholder(R.drawable.baseline_insert_photo_24).into(imageViewPerro);

                nombreTextView.setText(nombre);
                edadTextView.setText(edad);
                razaTextView.setText(raza);
                tamañoTextView.setText(tamaño);
                generoTextView.setText(genero);
                vac1TextView.setText(vac1);
                vac2TextView.setText(vac2);
                vac3TextView.setText(vac3);
                vac4TextView.setText(vac4);
            }

            cursor.close();
            db.close();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }
}
