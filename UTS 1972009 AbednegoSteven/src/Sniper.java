/**
 * @author AbednegoSteven - 1972009
 */
public class Sniper extends Tentara{
    public Sniper(int jumlah) {
        super(jumlah);
    }

    @Override
    public String toString() {
        return "Sniper dengan : "+getJumlah()+" jumlah elite sniper";
    }
}
