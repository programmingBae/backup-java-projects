package sample;

public class SampleThread extends Thread{

    private int maxx;
    private final ArrayClass ac;

    public SampleThread(ArrayClass ac, int m){
        this.ac = ac;
        maxx=m;
    }
    @Override
    public void run() {
        for(int i = maxx;i<maxx+3;i++){
           /* System.out.print(i);*/
            try {
                ac.tambahNilai(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
