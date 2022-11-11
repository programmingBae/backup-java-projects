/**
 * @author AbednegoSteven - 1972009
 */
public class Statistic {
    private int totalWin;
    private int totalLose;
    private int totalDraw;

    public void addWinStatistic(){
        this.totalWin += 1;
    }
    public void addDrawStatistic(){
        this.totalDraw +=1;
    }
    public void addLoseStatistic(){
        this.totalLose += 1;
    }
    public void showCompleteStatistic(){
        System.out.println("Total win : "+totalWin+", Total Draw : "+totalDraw+", Total Lose : "+totalLose);
    }
}
