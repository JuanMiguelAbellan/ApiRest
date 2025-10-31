package com.cpifppiramide.prueba1.entidades;

import java.util.ArrayList;
import java.util.List;

public class Prenda {

    private String marca;
    private TipoPrenda tipoPrenda;
    private List<Ejemplar> ejemplares;


    public Prenda(String marca, String tipoPrenda) {
        this.marca = marca;
        this.tipoPrenda = TipoPrenda.valueOf(tipoPrenda);
        this.ejemplares = new ArrayList<>();
    }

    public String getMarca() {
        return marca;
    }

    public TipoPrenda getTipoPrenda() {
        return tipoPrenda;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares){
        this.ejemplares = ejemplares;
    }

    public void addEjemplar(Ejemplar ejemplar){
        this.ejemplares.add(ejemplar);
    }
}
