package com.example.adopt;

public class Usuario {
    public Usuario() {
        //this.nome = nome;
        //this.email = email;
        //this.senha = senha;
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

    public void setEmail(String email) { this.email = email;}

    //public void setSenha(String senha) {
        //this.senha = senha;
    //}

    //public String getSenha() {
        //return senha;
    //}

    private String nome;
    private String email;
    //private String senha;
    private String sexo;
}
