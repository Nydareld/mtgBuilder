package com.theoguerin.mtgbuilder;

/**
 * Created by olivier on 27/01/17.
 */

public class Carte {
    String nom;
    String couleur;
    int mana;
    String rarete;

    public Carte(String nom, String couleur, int mana, String rarete){
        this.nom = nom;
        this.couleur = couleur;
        this.mana = mana;
        this.rarete = rarete;
    }

    public String getRarete() {
        return rarete;
    }

    public void setRarete(String rarete) {
        this.rarete = rarete;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
