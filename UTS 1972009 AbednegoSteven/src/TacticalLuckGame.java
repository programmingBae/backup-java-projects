/**
 * @author AbednegoSteven - 1972009
 */
import java.util.Scanner;
import java.util.Random;

public class TacticalLuckGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fighter ourChar = new Fighter();
        System.out.print("Player Fighter Name : ");
        String charName = sc.nextLine();
        ourChar.setName(charName);
        ourChar.setHealth(100);
        Fighter enemyChar = new Fighter("King Bowser");
        enemyChar.setHealth(100);
        Statistic statistic = new Statistic();
        int choice;
        Random random = new Random();
        do {
            System.out.println("------------------------------------------------------");
            System.out.println("T A C T I C A L \t\t L U C K \t\t G A M E");
            System.out.println("------------------------------------------------------");
            System.out.println("1. Fight");
            System.out.println("2. Show Game Status");
            System.out.println("3. Show My Statistic");
            System.out.println("4. Leave game");
            System.out.println("------------------------------------------------------");
            System.out.print("Choice : ");
            choice = sc.nextInt();
            if (choice==1){
                System.out.print("Choose [1]Attack [2]SP Attack [3]Defend : ");
                int choose = sc.nextInt();
                int x = random.nextInt(3); // Karena sampai 3 berarti generate sampai 2 (0,1,2)
                if (choose==1){
                    if (x == 0){
                        System.out.println("Stalemate, Player and computer attack");
                        ourChar.attack(enemyChar);
                        enemyChar.attack(ourChar);
                    }else if (x==1){
                        System.out.println("Player attack, computer defend");
                        enemyChar.defend(ourChar);
                    }else if (x==2) {
                        System.out.println("Player attack, computer SP attack");
                        ourChar.attack(enemyChar);
                    }

                }else if (choose==2){
                    if (x == 0){
                        System.out.println("Player SP attack, computer attack");
                        enemyChar.attack(ourChar);
                    }else if (x==1){
                        System.out.println("Stalemate, Player and computer attack");
                        ourChar.attack(enemyChar);
                        enemyChar.attack(ourChar);
                    }else if (x==2){
                        System.out.println("Player SP attack, computer defend");
                        ourChar.specialAttack(enemyChar);
                    }

                }else if (choose==3){
                    if (x==0){
                        System.out.println("Player defend, computer attack");
                        ourChar.defend(enemyChar);
                    }else if (x==1){
                        System.out.println("Player defend, computer SP attack");
                        enemyChar.specialAttack(ourChar);
                    }else if (x==2){
                        System.out.println("Stalemate, Player and computer attack");
                        ourChar.attack(enemyChar);
                        enemyChar.attack(ourChar);
                    }
                }
                if (ourChar.getHealth()<=0 && enemyChar.getHealth()>0){
                    statistic.addLoseStatistic();
                    ourChar.setHealth(100);
                    enemyChar.setHealth(100);
                }else if(ourChar.getHealth()>0 && enemyChar.getHealth()<=0){
                    statistic.addWinStatistic();
                    ourChar.setHealth(100);
                    enemyChar.setHealth(100);
                }else if (ourChar.getHealth()<=0 && enemyChar.getHealth()<=0){
                    statistic.addDrawStatistic();
                    ourChar.setHealth(100);
                    enemyChar.setHealth(100);
                }

            }else if (choice==2){
                System.out.println("Player's health : " + ourChar.getHealth());
                System.out.println("Computer's health : "+ enemyChar.getHealth());


            }else if (choice==3){
                statistic.showCompleteStatistic();
            }
        }while(choice!=4);
    }
}
