import java.util.Scanner;

public class PersonDemo {
    public static void main(String[] args) {
        FacultyMember member = new FacultyMember();
        Scanner sc= new Scanner(System.in);
        int choice;
        do{
            System.out.println("=================");
            System.out.println("1. Show all data");
            System.out.println("2. Add new lecturer");
            System.out.println("3. Add new student");
            System.out.println("4. Exit");
            System.out.print("Choice : ");
            choice = sc.nextInt();
            if (choice == 1){
                member.showAll();
            }
            else if(choice == 2){
                sc.nextLine();
                System.out.print("Name : ");
                String name = sc.nextLine();
                System.out.print("NIDN : ");
                String nidn = sc.nextLine();
                member.addPerson(new Lecturer(name,nidn));
                System.out.println("Insert Success");
            }
            else if(choice == 3){
                sc.nextLine();
                System.out.print("Name : ");
                String name = sc.nextLine();
                System.out.print("NRP : ");
                int nrp = sc.nextInt();
                sc.nextLine();
                member.addPerson(new Student(name,nrp));
                System.out.println("Insert Success");
            }
        }while(choice!=4);
    }
}
