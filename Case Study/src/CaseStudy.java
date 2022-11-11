/**
 * @author Abednego Steven - 1972009
 */

import java.util.Arrays;
import java.util.Scanner;
public class CaseStudy {
    public static void main(String[] args) {
        // pembuatan array bertipe integer
        Scanner sc = new Scanner(System.in);
        int[] integer = new int[5];
        System.out.println("## Proses Input angka ##");
        for (int i=0;i<5;i++ ){
            System.out.print("Input array ke-"+(i+1)+": ");
            integer [i] = sc.nextInt();
    }
        System.out.println("## Angka Hasil Input ##");
        Arrays.stream(integer).forEach(System.out::println);
        System.out.println("## Array setelah diurutkan ##");
        Arrays.sort(integer);
        Arrays.stream(integer).forEach(number -> System.out.println(number));


}}
