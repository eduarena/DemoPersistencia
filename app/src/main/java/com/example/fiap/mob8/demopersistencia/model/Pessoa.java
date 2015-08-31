package com.example.fiap.mob8.demopersistencia.model;

/**
 * Created by rm49684 on 26/08/2015.
 */
public class Pessoa {

    private Long id; //É uma classe e nao primitivo
    private String nome;
    private int idade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
