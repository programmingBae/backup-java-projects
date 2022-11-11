/**
 * @author AbednegoSteven - 1972009
 */
package com.abednego.entity;

public class Elips extends BangunDatar{
    @Override
    public double getKeliling() {
        if (getPanjang()==getLebar()){
            return 2*Math.PI*getPanjang();
        } else{
            double c = (getPanjang()*getPanjang())+(getLebar()*getLebar());
            double d = c/2;
            return 2*Math.PI*Math.sqrt(d);
        }
    }

    @Override
    public double getLuas() {
        if (getPanjang()==getLebar()){
            return Math.PI * (getPanjang()*getPanjang());
        } else {
            return Math.PI * getPanjang() * getLebar();

        }
    }

    @Override
    public String toString() {
        return "Elips";

    }
}
