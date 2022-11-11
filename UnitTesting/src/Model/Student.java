package Model;

public class Student {

    private String nama;
    private int nilai;

    public String cekNilai(){
        if (nilai<50){
            return "jelek";
        } else if (nilai<90) {
            return "lumayan";
        } else {
            return "luar biasa";
        }
    }

    public Student(String nama, int nilai) {
        this.nama = nama;
        this.nilai = nilai;
    }

    public int getNilaiTimesTen(){
        return nilai * 10;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}
