package com.example.petrescue;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultarActivity extends AppCompatActivity {

    private Button buscarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        buscarBtn = findViewById(R.id.btn_buscar);

        buscarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario(v);
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

}