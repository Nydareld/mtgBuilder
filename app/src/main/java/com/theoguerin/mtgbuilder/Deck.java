package com.theoguerin.mtgbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 27/01/17.
 */

public class Deck {
    String nom;
    List<Carte> cartes;

    public Deck(String nom){
        this.nom = nom;
        this.cartes = new ArrayList<Carte>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    public void addCarte(Carte carte){
        this.cartes.add(carte);
    }

    public Carte getCarte(int i){
        return this.cartes.get(i);
    }

    public void deleteCarte(int i ){
        this.cartes.remove(i);
    }

    public void deleteCarte(Carte carte){
        this.cartes.remove(carte);
    }

    public int getNombreCartes(){
        return this.cartes.size();
    }
}
