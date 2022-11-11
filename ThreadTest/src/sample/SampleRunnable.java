package sample;

public class SampleRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("runnable run: ");
        for(int i = 0;i<10;i++){
            System.out.print(i);
        }
        System.out.println();
    }
}
