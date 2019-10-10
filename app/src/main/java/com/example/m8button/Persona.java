package com.example.m8button;

import android.graphics.drawable.DrawableWrapper;

public class Persona {
    String nom;
    int qualificacio;


    public Persona(String nom, int qualificacio) {
        this.nom = nom;
        this.qualificacio = qualificacio;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom+" - "+qualificacio;
    }
}
