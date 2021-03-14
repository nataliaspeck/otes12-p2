package com.natalia.otes12;

/**
 * Classe que representa Funcionário que implementa métodos concredos das operações definidas no Command.
 */
public class Funcionario extends FuncionarioCommand {

    String nome;
    String regiao;
    Cota cota;

    public Funcionario(String nome, String regiao) {
        super(nome, regiao);
        this.nome = nome;
        this.regiao = regiao;
   }

    public void setCota(String trimestre, Double cota, Double vendas) {
        CotaBuilder cotaBuilder = new CotaBuilder();

        cotaBuilder.setTrimestre(trimestre);
        cotaBuilder.setCota(cota);
        cotaBuilder.setVendas(vendas);

        this.cota = cotaBuilder.buildCota();
    }

    public void updateProgresso(Double vendas) {
        System.out.println("Atualizando o progresso de: " + this.nome);
        this.cota.setVendas(vendas);
    }

    public void imprimirRelatorio() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Funcionário {" +
                "NOME = '" + nome + '\'' +
                ", REGIÃO ='" + regiao + '\'' +
                ", COTA = " + cota +
                '}';
    }
}