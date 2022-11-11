/**
 * @author AbednegoStevwen - 1972009
 */
package com.abednego.entity;

public abstract class BangunDatar {
    private double panjang;
    private double lebar;

    public double getPanjang() {
        return panjang;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public double getLebar() {
        return lebar;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public abstract double getKeliling();
    public abstract double getLuas();

}
