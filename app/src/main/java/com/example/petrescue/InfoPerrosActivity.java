package com.example.petrescue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.File;

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
    private TextView textView_protector;
    private TextView textView_tf;
    private TextView textView_email;
    private MyOpenHelper myOpenHelper;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_perros);
        toolbar_info = findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar_info);
        myOpenHelper = new MyOpenHelper(this);
        Button btn_solic = findViewById(R.id.btn_solicitud);

        btn_solic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPerrosActivity.this, SolicAdopcionActivity.class);
                startActivity(intent);
            }
        });

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
        textView_protector = findViewById(R.id.textView_protector);
        textView_tf = findViewById(R.id.textView_tf);
        textView_email = findViewById(R.id.textView_email);

        int perroId = getIntent().getIntExtra("perro_id", -1);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (db != null) {
            String[] projection = {"nombre", "edad", "raza", "tamaño", "genero", "vac1", "vac2", "vac3", "vac4", "imagen_path", "usuario_id"};
            String selection = "id = ?";
            String[] selectionArgs = {String.valueOf(perroId)};
            Cursor cursor = db.query("dogs", projection, selection, selectionArgs, null, null, null);
            System.out.println("Prueba 1");
            int usuario_id = 0;
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
                System.out.println("Prueba 2");
                String imagenPath = cursor.getString(cursor.getColumnIndexOrThrow("imagen_path"));
                usuario_id = cursor.getInt(cursor.getColumnIndexOrThrow("usuario_id"));
                System.out.println("Usuario: " + usuario_id);
                File imageFile = new File(imagenPath);
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageViewPerro.setImageBitmap(bitmap);

                nombreTextView.setText(nombre);
                edadTextView.setText(edad);
                razaTextView.setText(raza);
                tamañoTextView.setText(tamaño);
                generoTextView.setText(genero);
                vac1TextView.setText(vac1);
                vac2TextView.setText(vac2);
                vac3TextView.setText(vac3);
                vac4TextView.setText(vac4);
                validateLogin(usuario_id);
            }
            cursor.close();
            db.close();
        }
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
            Intent adoptarIntent = new Intent(InfoPerrosActivity.this, MainAdoptarActivity.class);
            startActivity(adoptarIntent);
            return true;
        } else if (id == R.id.opc2) {
            // Acción para la opción "Dar en adopción"
            Intent darEnAdopcionIntent = new Intent(InfoPerrosActivity.this, MainAdopcionActivity.class);
            startActivity(darEnAdopcionIntent);
            return true;
        } else if (id == R.id.opc3) {
            // Acción para la opción "Cerrar sesión"
            Intent salirIntent = new Intent(InfoPerrosActivity.this, LoginActivity.class);
            startActivity(salirIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("Range")
    private boolean validateLogin(int userId) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();

        String query = "SELECT * FROM usuarios WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        boolean loginSuccessful = cursor.moveToFirst();
        if (loginSuccessful) {
            userId = cursor.getInt(cursor.getColumnIndex("id"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            System.out.println("email: " + email);
            String protector = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String tf = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            textView_protector.setText(protector);
            textView_tf.setText(tf);
            textView_email.setText(email);
        }
        cursor.close();
        db.close();
        return loginSuccessful;
    }
}
