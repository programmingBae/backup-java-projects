package com.eriko.model;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Peminjaman {
    private Integer id;
    private Date tanggalPinjam;
    private Date tanggalPengembalian;
    private Buku buku;
    private Anggota anggota;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tanggalPinjam", nullable = true)
    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    @Basic
    @Column(name = "tanggalPengembalian", nullable = true)
    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peminjaman that = (Peminjaman) o;
        return Objects.equals(id, that.id) && Objects.equals(tanggalPinjam, that.tanggalPinjam) && Objects.equals(tanggalPengembalian, that.tanggalPengembalian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tanggalPinjam, tanggalPengembalian);
    }

    @ManyToOne
    @JoinColumn(name = "Buku_id", referencedColumnName = "id", nullable = false)
    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    @ManyToOne
    @JoinColumn(name = "Anggota_id", referencedColumnName = "id", nullable = false)
    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }
}
