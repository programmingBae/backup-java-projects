package com.abednego.entity;

/**
 * @author - AbednegoSteven - 1972009
 */

public class Barang {
    private int id;
    private String nama;
    private Supplier supplier;

    public Barang() {
    }

    public Barang(int id, String nama, Supplier supplier) {
        this.id = id;
        this.nama = nama;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
