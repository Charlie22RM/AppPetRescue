package com.example.petrescue;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultarActivity extends AppCompatActivity {

    private Button buscarBtn;
    private Button editarBtn;
    private Button eliminarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        buscarBtn = findViewById(R.id.btn_buscar);
        editarBtn = findViewById(R.id.btn_editar);
        eliminarBtn = findViewById(R.id.btn_eliminar);

        buscarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarUsuario(v);
            }
        });

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editarUsuario(v);
            }
        });

        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarUsuario(v);
            }
        });
    }
    @SuppressLint("Range")
    public void buscarUsuario(View v){

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (db != null){

            EditText id_tmp = (EditText) findViewById(R.id.edit_id);
            EditText nombre_tmp = (EditText) findViewById(R.id.edit_nombre);
            EditText apellido_tmp = (EditText) findViewById(R.id.edit_apellido);
            EditText email_tmp = (EditText) findViewById(R.id.edit_email);
            EditText password_tmp = (EditText) findViewById(R.id.edit_password);

            int identificador = Integer.parseInt(id_tmp.getText().toString());
            Cursor c = db.rawQuery("SELECT _id, nombre, apellido, email, password FROM usuarios WHERE _id="+ identificador, null);

            if (c != null){

                c.moveToFirst();
                nombre_tmp.setText(c.getString(c.getColumnIndex("nombre")).toString());
                apellido_tmp.setText(c.getString(c.getColumnIndex("apellido")).toString());
                email_tmp.setText(c.getString(c.getColumnIndex("email")).toString());
                password_tmp.setText(c.getString(c.getColumnIndex("password")).toString());
            }
            c.close();
            db.close();
        }

    }

    public void editarUsuario(View v){
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            EditText id_tmp = findViewById(R.id.edit_id);
            EditText nombre_tmp = findViewById(R.id.edit_nombre);
            EditText apellido_tmp = findViewById(R.id.edit_apellido);
            EditText email_tmp = findViewById(R.id.edit_email);
            EditText password_tmp = findViewById(R.id.edit_password);

            int identificador = Integer.parseInt(id_tmp.getText().toString());

            ContentValues cv = new ContentValues();
            cv.put("nombre", nombre_tmp.getText().toString());
            cv.put("apellido", apellido_tmp.getText().toString());
            cv.put("email", email_tmp.getText().toString());
            cv.put("password", password_tmp.getText().toString());

            int rowsAffected = db.update("usuarios", cv, "_id=?", new String[]{String.valueOf(identificador)});
            if (rowsAffected > 0) {
                Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }
    }

    public void eliminarUsuario(View v){
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            EditText id_tmp = findViewById(R.id.edit_id);
            int identificador = Integer.parseInt(id_tmp.getText().toString());

            int rowsAffected = db.delete("usuarios", "_id=?", new String[]{String.valueOf(identificador)});
            if (rowsAffected > 0) {
                Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

    }

}