/**
 * @author AbednegoSteven - 1972009
 */


public class Tentara {
    private int jumlah;

    public Tentara(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah(){
        return jumlah;
    }
    public void setJumlah(int jumlah){
        if (jumlah<0){
            this.jumlah = 0;
        }else{
            this.jumlah = jumlah;
        }
    }
    public String toString(){
        return "Tentara dengan : "+getJumlah()+" jumlah personel";
    }
}
