package com.example.petrescue;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.btn_crear_cuenta);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario(v);
            }
        });

    }

    public void guardarUsuario(View v){

        EditText nombre_tmp = (EditText) findViewById(R.id.txt_nombre);
        EditText apellido_tmp = (EditText) findViewById(R.id.txt_apellido);
        EditText telefono_tmp = (EditText) findViewById(R.id.txt_telefono);
        EditText direccion_tmp = (EditText) findViewById(R.id.txt_direccion);
        EditText email_tmp = (EditText) findViewById(R.id.txt_email);
        EditText password_tmp = (EditText) findViewById(R.id.txt_password);

        MyOpenHelper dbHelper = new MyOpenHelper(v.getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null){
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombre_tmp.getText().toString());
            cv.put("apellido", apellido_tmp.getText().toString());
            cv.put("telefono", telefono_tmp.getText().toString());
            cv.put("direccion", direccion_tmp.getText().toString());
            cv.put("email", email_tmp.getText().toString());
            cv.put("password", password_tmp.getText().toString());
            db.insert("usuarios", null, cv);
            Toast.makeText(v.getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
        //db.close();
    }
}