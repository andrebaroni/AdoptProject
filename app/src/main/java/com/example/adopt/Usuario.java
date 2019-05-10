package com.example.adopt;

public class Usuario {
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        //this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    private String nome;
    private String email;
    private String senha;
    private String sexo;
}
