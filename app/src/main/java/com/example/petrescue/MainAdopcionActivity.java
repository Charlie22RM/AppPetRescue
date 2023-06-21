package com.example.petrescue;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainAdopcionActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adopcion);
        toolbar = findViewById(R.id.toolbar_adopcion);
        setSupportActionBar(toolbar);

        Spinner tamano = findViewById(R.id.spn_tamano);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_tamano,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tamano.setAdapter(adapter);

        registerBtn = findViewById(R.id.btn_register_dogs);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarMascota(v);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }

    public void guardarMascota(View v) {

        EditText nombre_perro_tmp = (EditText) findViewById(R.id.txt_nombre_perro);
        EditText edad_perro_tmp = (EditText) findViewById(R.id.txt_edad_perro);
        EditText raza_perro_tmp = (EditText) findViewById(R.id.txt_raza_perro);


        Spinner tamanoSpinner = findViewById(R.id.spn_tamano);
        String tamano = tamanoSpinner.getSelectedItem().toString();

        RadioGroup generoRadioGroup = findViewById(R.id.rg_genero);
        int selectedId = generoRadioGroup.getCheckedRadioButtonId();
        String genero = "";

        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            genero = radioButton.getText().toString();
        }

        CheckBox vacuna1Checkbox = findViewById(R.id.vac1);
        boolean vacuna1 = vacuna1Checkbox.isChecked();

        CheckBox vacuna2Checkbox = findViewById(R.id.vac2);
        boolean vacuna2 = vacuna2Checkbox.isChecked();

        CheckBox vacuna3Checkbox = findViewById(R.id.vac3);
        boolean vacuna3 = vacuna3Checkbox.isChecked();

        CheckBox vacuna4Checkbox = findViewById(R.id.vac4);
        boolean vacuna4 = vacuna4Checkbox.isChecked();

        MyOpenHelper dbHelper = new MyOpenHelper(v.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombre_perro_tmp.getText().toString());
            cv.put("edad", edad_perro_tmp.getText().toString());
            cv.put("raza", raza_perro_tmp.getText().toString());
            cv.put("tamaño", tamano);
            cv.put("genero", genero);
            cv.put("vac1", vacuna1 ? "Rabia" : "No");
            cv.put("vac2", vacuna2 ? "Parvovirus canino" : "No");
            cv.put("vac3", vacuna3 ? "Moquillo" : "No");
            cv.put("vac4", vacuna4 ? "Hepatitis canina" : "No");

            db.insert("dogs", null, cv);
            Toast.makeText(v.getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }

        //db.close();
    }
}