package com.example.demo.Entities;

public class Penerbangan {
    private int idPenerbangan;
    private float HargaPenerbangan;
    private Negara Negara_idAsal;
    private Negara Negara_idTujuan;
    private String Maskapai;

    public Penerbangan(int idPenerbangan, float hargaPenerbangan, Negara negara_idAsal, Negara negara_idTujuan, String maskapai) {
        this.idPenerbangan = idPenerbangan;
        HargaPenerbangan = hargaPenerbangan;
        Negara_idAsal = negara_idAsal;
        Negara_idTujuan = negara_idTujuan;
        Maskapai = maskapai;
    }

    public Penerbangan() {
    }

    public int getIdPenerbangan() {
        return idPenerbangan;
    }

    public void setIdPenerbangan(int idPenerbangan) {
        this.idPenerbangan = idPenerbangan;
    }

    public float getHargaPenerbangan() {
        return HargaPenerbangan;
    }

    public void setHargaPenerbangan(float hargaPenerbangan) {
        HargaPenerbangan = hargaPenerbangan;
    }

    public Negara getNegara_idAsal() {
        return Negara_idAsal;
    }

    public void setNegara_idAsal(Negara negara_idAsal) {
        Negara_idAsal = negara_idAsal;
    }

    public Negara getNegara_idTujuan() {
        return Negara_idTujuan;
    }

    public void setNegara_idTujuan(Negara negara_idTujuan) {
        Negara_idTujuan = negara_idTujuan;
    }

    public String getMaskapai() {
        return Maskapai;
    }

    public void setMaskapai(String maskapai) {
        Maskapai = maskapai;
    }
}
