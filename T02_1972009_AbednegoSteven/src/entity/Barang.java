package entity;

/**
 * @author - Abednego Steven - 1972009
 */

public class Barang {
    private String nama;
    private int harga;
    private Supplier supplier;

    public Barang (){

    }

    public Barang(String nama, int harga, Supplier supplier) {
        this.nama = nama;
        this.harga = harga;
        this.supplier = supplier;
    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
