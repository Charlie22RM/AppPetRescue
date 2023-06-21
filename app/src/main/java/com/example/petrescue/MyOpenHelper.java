package com.example.petrescue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String USER_TABLE_CREATE = "CREATE TABLE usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, telefono TEXT, direccion TEXT, email TEXT, password TEXT)";
    private static final String DOGS_TABLE_CREATE = "CREATE TABLE dogs(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad TEXT, raza TEXT, tamaño TEXT, genero TEXT, vac1 TEXT, vac2 TEXT, vac3 TEXT, vac4 TEXT)";
    private static final String DB_NAME = "datosPrueba.sqlite";
    private static final int DB_VERSION = 1;
    public MyOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(DOGS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

