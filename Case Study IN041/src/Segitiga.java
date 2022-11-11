/**
 * Abednego Steven - 1972009
*/
public class Segitiga {
    private double alas, tinggi;

    public Segitiga(){

    }

    public Segitiga(double alas, double tinggi){
        this.alas = alas;
        this.tinggi = tinggi;
    }

    public double getAlas() {
        return alas;
    }

    public void setAlas(double alas) {
        this.alas = alas;
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    public double getLuasSegitiga(){
        return alas * tinggi / 2;
    }
}
