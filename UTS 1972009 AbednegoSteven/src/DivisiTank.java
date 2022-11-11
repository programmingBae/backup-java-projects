/**
 * @author AbednegoSteven - 1972009
 */
import java.util.ArrayList;
import java.util.Arrays;


public class DivisiTank extends Tentara{
    private int jumlahTank;


    public DivisiTank(int jumlah, int jumlahTank) {
        super(jumlah);
        this.jumlahTank = jumlahTank;
    }

    public int getJumlahTank() {
        return jumlahTank;
    }

    public void setJumlahTank(int jumlahTank) {
        this.jumlahTank = jumlahTank;
    }
    public int getPersonel(){
        return getJumlah()-(jumlahTank*4);
    }

    @Override
    public String toString() {
        return "Tank dengan : "+getJumlahTank()+" jumlah tank dan "+getJumlah()+" jumlah personel kelebihan "+getPersonel()+" orang";
    }
}
