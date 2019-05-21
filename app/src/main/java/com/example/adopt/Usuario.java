package com.example.adopt;

public class Usuario {
    public Usuario() {}

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

    public Boolean getAnimal() {
        return isAnimal;
    }

    public void setAnimal(Boolean isAnimal) {
        this.isAnimal = isAnimal;
    }

    private String nome;
    private String email;
    private String sexo;
    private Boolean isAnimal;
}
