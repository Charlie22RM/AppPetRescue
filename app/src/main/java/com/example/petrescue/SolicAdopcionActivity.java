package com.example.petrescue;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SolicAdopcionActivity extends AppCompatActivity {

    private Toolbar toolbar_solic;

    private Button solicitudBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solic_adopcion);
        toolbar_solic = findViewById(R.id.toolbar_solic);
        setSupportActionBar(toolbar_solic);

        solicitudBtn = findViewById(R.id.btn_solicitudes);
        solicitudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarSolicitud(v);
            }
        });

    }

    public void guardarSolicitud(View v) {

        EditText nombres_tmp = (EditText) findViewById(R.id.txt_nombres);
        EditText numero_tmp = (EditText) findViewById(R.id.txt_numero);
        EditText interes_tmp = (EditText) findViewById(R.id.txt_interes);

        RadioGroup viviendaRadioGroup = findViewById(R.id.rg_vivienda);
        int vselectedId = viviendaRadioGroup.getCheckedRadioButtonId();
        String vivienda = "";

        if (vselectedId != -1) {
            RadioButton radioButton = findViewById(vselectedId);
            vivienda = radioButton.getText().toString();
        }

        RadioGroup espacioRadioGroup = findViewById(R.id.rg_espacio);
        int eselectedId = espacioRadioGroup.getCheckedRadioButtonId();
        String espacio = "";

        if (eselectedId != -1) {
            RadioButton radioButton = findViewById(eselectedId);
            espacio = radioButton.getText().toString();
        }

        RadioGroup tiempoRadioGroup = findViewById(R.id.rg_tiempo);
        int tselectedId = tiempoRadioGroup.getCheckedRadioButtonId();
        String tiempo = "";

        if (eselectedId != -1) {
            RadioButton radioButton = findViewById(tselectedId);
            tiempo = radioButton.getText().toString();
        }


        MyOpenHelper dbHelper = new MyOpenHelper(v.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombres_tmp.getText().toString());
            cv.put("telefono", numero_tmp.getText().toString());
            cv.put("interes", interes_tmp.getText().toString());
            cv.put("vivienda", vivienda);
            cv.put("espacio", espacio);
            cv.put("tiempo", tiempo);

            db.insert("solicitudes", null, cv);
            Toast.makeText(v.getContext(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
        }

        //db.close();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }
}