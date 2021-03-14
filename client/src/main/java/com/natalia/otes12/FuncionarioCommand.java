package com.natalia.otes12;

/**
 * GOF: Command
 */
public abstract class FuncionarioCommand {

    String nome;
    String regiao;

    FuncionarioCommand(String nome, String regiao) {
        this.nome = nome;
        this.regiao = regiao;
    }

    /**
     * Imprime o relatório de qualquer tipo de funcionário
     */
    public abstract void imprimirRelatorio();

}