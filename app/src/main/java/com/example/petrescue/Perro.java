package com.example.petrescue;

public class Perro {
    private int id;
    private String nombre;
    private String raza;
    private String imagenPath;

    public Perro(int id, String nombre, String raza, String imagenPath) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.imagenPath = imagenPath;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRaza() {
        return raza;
    }
    public String getImagenPath() {
        System.out.println("Ruta de la imagen: " + imagenPath);
        return imagenPath;
    }
}