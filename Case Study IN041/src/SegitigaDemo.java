/**
 * @author Abednego Steven - 1972009
 */
import java.util.Scanner;

public class SegitigaDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Segitiga segitiga1 = new Segitiga (10,6);
        int pilihan;
        do{
            System.out.println("====================");
            System.out.println("1. Ubah tinggi segitiga");
            System.out.println("2. Ubah alas segitiga");
            System.out.println("3. Lihat tinggi segitiga");
            System.out.println("4. Lihat alas segitiga");
            System.out.println("5. Hitung luas segitiga");
            System.out.println("6. Exit");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if (pilihan == 1){
                System.out.print("Tinggi setiga yang baru : ");
                double tinggiBaru = scanner.nextDouble();
                segitiga1.setTinggi(tinggiBaru);

            }
            else if(pilihan == 2){
                System.out.print("Alas setiga yang baru : ");
                double alasBaru = scanner.nextDouble();
                segitiga1.setAlas(alasBaru);

            }
            else if(pilihan == 3){
                System.out.println("Tinggi segitiga : " + segitiga1.getTinggi());

            }
            else if(pilihan == 4){
                System.out.println("Alas segitiga : " + segitiga1.getAlas());

            }
            else if(pilihan == 5){
                System.out.println("Luas segitiga : " + segitiga1.getLuasSegitiga());

            }}
        while(pilihan != 6);
    }
}
