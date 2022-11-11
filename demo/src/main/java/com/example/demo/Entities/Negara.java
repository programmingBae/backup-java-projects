package com.example.demo.Entities;

public class Negara {
    private int id;
    private String Negara;

    public Negara() {
    }

    public Negara(int id, String negara) {
        this.id = id;
        Negara = negara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNegara() {
        return Negara;
    }

    public void setNegara(String negara) {
        Negara = negara;
    }

    @Override
    public String toString() {
        return  Negara;
    }
}
