/**
 * @author AbednegoSteven - 1972009
 */
package com.abednego.entity;

public class Persegi extends BangunDatar{
    @Override
    public double getKeliling() {
        return getPanjang()+getLebar()+getPanjang()+getLebar();
    }

    @Override
    public double getLuas() {
        return getPanjang()*getLebar();
    }

    @Override
    public String toString() {
        return "Persegi";
    }
}
