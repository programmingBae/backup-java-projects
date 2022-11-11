/**
 * @author AbednegoSteven - 1972009
 */

import com.sun.org.apache.xpath.internal.operations.Div;

import java.util.Scanner;
import java.util.ArrayList;

public class TentaraDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Tentara> tentara;
        tentara =new ArrayList<>();
        int choice;
        do{
            System.out.println("-----------------------------------");
            System.out.println("Menu");
            System.out.println("-----------------------------------");
            System.out.println("1. Tambah tentara");
            System.out.println("2. Tambah divisi tank");
            System.out.println("3. Tambah sniper");
            System.out.println("4. Show tentara");
            System.out.println("5. Update data");
            System.out.println("6. Transfer prajurit");
            System.out.println("7. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Input : ");
            choice = sc.nextInt();
            if (choice == 1){
                System.out.print("Masukkan jumlah tentara : ");
                int jumlahTentara = sc.nextInt();
                tentara.add(0,new Tentara(jumlahTentara));

            }else if (choice == 2){
                System.out.print("Masukkan jumlah personel : ");
                int jumlahPersonel = sc.nextInt();
                System.out.print("Masukkan jumlah tank : ");
                int jumlahTank = sc.nextInt();
                tentara.add(1,new DivisiTank(jumlahPersonel,jumlahTank));

            }else if (choice ==3){
                System.out.print("Masukkan jumlah sniper : ");
                int jumlahSniper = sc.nextInt();
                tentara.add(2,new Sniper(jumlahSniper));

            }else if (choice ==4){
                tentara.forEach(Tentara ->{
                    if (Tentara instanceof Tentara){
                        System.out.printf("%5s%20s",tentara.indexOf(((Tentara) Tentara)),((Tentara) Tentara).toString());
                        System.out.println();
                    }
                });
            }else if (choice == 5){
                tentara.forEach(Tentara ->{
                    if (Tentara instanceof Tentara){
                        System.out.printf("%5s%20s",tentara.indexOf(((Tentara) Tentara)),((Tentara) Tentara).toString());
                        System.out.println();
                    }
                });
                System.out.print("Masukkan data yang hendak diubah : ");
                int data = sc.nextInt();
                if (data == 0){
                    System.out.print("Masukkan jumlah tentara : ");
                    int jumlahTentara = sc.nextInt();
                    tentara.get(0).setJumlah(jumlahTentara);
                }else if (data==1){
                    System.out.print("Masukkan jumlah personel : ");
                    int jumlahPersonel = sc.nextInt();
                    tentara.get(1).setJumlah(jumlahPersonel);
                    System.out.print("Masukkan jumlah tank baru : ");
                    int jumlahTank = sc.nextInt();
                    tentara.get(1).setJumlah(jumlahTank);
                }else if (data==2){
                    System.out.print("Masukkan jumlah sniper baru : ");
                    int jumlahSniper = sc.nextInt();
                    tentara.get(2).setJumlah(jumlahSniper);
                }

            }else if (choice == 6){
                tentara.forEach(Tentara ->{
                    if (Tentara instanceof Tentara){
                        System.out.printf("%5s%20s",tentara.indexOf(((Tentara) Tentara)),((Tentara) Tentara).toString());
                        System.out.println();
                    }
                });
                System.out.print("Masukkan no sumber tentara : ");
                int sumber = sc.nextInt();
                System.out.print("Masukkan no target tentara : ");
                int target = sc.nextInt();
                System.out.print("Masukkan jumlah transfer : ");
                int jumlahTransfer = sc.nextInt();
                if (sumber == 0 && target == 1){
                    tentara.get(0).setJumlah(tentara.get(0).getJumlah()-jumlahTransfer);
                    tentara.get(1).setJumlah(tentara.get(1).getJumlah()+jumlahTransfer);
                }else if (sumber == 0 && target == 2){
                    tentara.get(0).setJumlah(tentara.get(0).getJumlah()-jumlahTransfer);
                    tentara.get(1).setJumlah(tentara.get(1).getJumlah()+jumlahTransfer);
                }else if (sumber == 1 && target == 0){
                    tentara.get(1).setJumlah(tentara.get(0).getJumlah()-jumlahTransfer);
                    tentara.get(0).setJumlah(tentara.get(1).getJumlah()+jumlahTransfer);
                }else if (sumber == 1 && target == 2){
                    tentara.get(1).setJumlah(tentara.get(2).getJumlah()-jumlahTransfer);
                    tentara.get(2).setJumlah(tentara.get(1).getJumlah()+jumlahTransfer);
                }else if (sumber == 2 && target == 0){
                    tentara.get(2).setJumlah(tentara.get(0).getJumlah()-jumlahTransfer);
                    tentara.get(0).setJumlah(tentara.get(2).getJumlah()+jumlahTransfer);
                }else if (sumber == 2 && target == 1){
                    tentara.get(2).setJumlah(tentara.get(1).getJumlah()-jumlahTransfer);
                    tentara.get(1).setJumlah(tentara.get(2).getJumlah()+jumlahTransfer);
                }
                System.out.println("Data Updated");
            }
        }while(choice!=7);
    }
}
