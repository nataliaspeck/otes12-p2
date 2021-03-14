package com.natalia.otes12;

/**
 * Builder concreto que implementa as operações definidas na interface comum.
 */
public class CotaBuilder implements Builder {

    String trimestre;
    Double cota;
    Double vendas;

    @Override
    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    @Override
    public void setCota(Double cota) {
        this.cota = cota;
    }

    @Override
    public void setVendas(Double vendas) {
        this.vendas = vendas;
    }

    public Cota buildCota() {
        return new Cota(trimestre, cota, vendas);
    }
}