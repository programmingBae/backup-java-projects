package entity;

/**
 * @author - Abednego Steven - 1972009
 */

public class Supplier {
    private String nama;

    public Supplier (){

    }

    public Supplier(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}
