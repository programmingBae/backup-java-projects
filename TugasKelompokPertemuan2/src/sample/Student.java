package sample;

public class Student {

    private String nama;
    private double KAT;
    private double UAS;
    private double UTS;
    private double rata;

    public Student() {
    }



    public Student(String nama, int KAT, int UAS, int UTS) {
        this.nama = nama;
        this.KAT = KAT;
        this.UAS = UAS;
        this.UTS = UTS;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getKAT() {
        return KAT;
    }

    public void setNilaiKAT(double KAT) {
        this.KAT = KAT;
    }

    public double getUAS() {
        return UAS;
    }

    public void setNilaiUAS(double UAS) {
        this.UAS = UAS;
    }

    public double getUTS() {
        return UTS;
    }

    public void setNilaiUTS(double UTS) {
        this.UTS = UTS;
    }

    public String getStringKAT() {
        return String.valueOf(getKAT());
    }

    public String getStringUTS() {
        return String.valueOf(getUTS());
    }

    public String getStringUAS() {
        return String.valueOf(getUAS());
    }

    public String getStringRata() {
        return String.valueOf(getRata());
    }

    public double getRata() {
        double rata = (0.5 * getKAT()) + (0.25 * getUTS()) + (0.25 * getUAS());
        return rata;
    }

}
