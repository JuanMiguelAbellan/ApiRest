package com.cpifppiramide.prueba1.entidades;

import java.io.Serializable;
import java.util.List;

public class PrendaDTO implements Serializable {

    private String marca;
    private TipoPrenda tipoPrenda;
    private List<Ejemplar> ejemplares;
    private Color color;
    private Talla talla;
    private Integer stock;

    public PrendaDTO(String marca, String tipoPrenda){
        setMarca(marca);
        setTipoPrenda(tipoPrenda);
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTipoPrenda(String tipoPrenda) {
        try {
            this.tipoPrenda= TipoPrenda.valueOf(tipoPrenda);
        } catch (IllegalArgumentException e) {
            this.tipoPrenda=TipoPrenda.Camiseta;
            System.out.println("El tipo de prenda no es valido se usara el tipo de prenda por defecto Camiseta");
        }
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setColor(String color) {
        try {
            this.color= Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            System.out.println("El color no es valido");
        }
    }

    public void setTalla(String talla) {
        try {
            this.talla= Talla.valueOf(talla);
        } catch (IllegalArgumentException e) {
            System.out.println("La talla no es valida");
        }
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipoPrenda() {
        return tipoPrenda.toString();
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public Color getColor() {
        return color;
    }

    public Talla getTalla() {
        return talla;
    }

    public Integer getStock() {
        return stock;
    }
}
