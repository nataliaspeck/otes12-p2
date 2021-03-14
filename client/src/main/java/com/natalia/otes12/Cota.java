package com.natalia.otes12;

/**
 * Classe que representa Cota
 */
public class Cota {

    String trimestre;
    Double cota;
    Double vendas;

    public Cota(String trimestre, Double cota, Double vendas) {
        this.trimestre = trimestre;
        this.cota = cota;
        this.vendas = vendas;
    }

    public void setVendas(Double vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "{" +
                "TRIMESTRE = '" + trimestre + '\'' +
                ", COTA = " + cota +
                ", VENDAS = " + vendas +
                '}';
    }
}