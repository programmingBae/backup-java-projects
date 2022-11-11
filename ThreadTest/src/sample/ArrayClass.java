package sample;

import java.util.Arrays;

public class ArrayClass {
    private final int[] arrayInt;
    private int position;


    public ArrayClass( int size) {
        this.arrayInt = new int[size];

    }

    public void tambahNilai(int v) throws InterruptedException{
        int my_pos = position;
        Thread.sleep(500);
        System.out.printf("%s memasukkan nilai %d di posisi %d\n", Thread.currentThread().getName(),v,my_pos);
        arrayInt[my_pos] = v;
        position++;
        System.out.println("posisi berikutnya : "+position);
    }

    @Override
    public String toString() {
        return Arrays.toString(arrayInt);
    }
}
