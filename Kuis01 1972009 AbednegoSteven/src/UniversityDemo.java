import java.util.Locale;
import java.util.Scanner;

/**
 * @author AbednegoSteven - 1972009
 */
public class UniversityDemo {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        Admin admin = new Admin();
        University maranatha = new University("Universitas Kristen Maranatha"," Jl. Surya Soemantri");
        admin.setUniversity(maranatha);
        System.out.println("===== Please login ======");
        System.out.print("Username : ");
        String uname = sc.next();
        admin.setUsername(uname);
        System.out.print("Password : ");
        String password = sc.next();
        admin.setPassword(password);
        while(!admin.login()){
            System.out.println("Wrong username or password!");
            System.out.println("===== Please login ======");
            System.out.print("Username : ");
            uname = sc.next();
            admin.setUsername(uname);
            System.out.print("Password : ");
            password = sc.next();
            admin.setPassword(password);
        }
        int choice;
        do{
            System.out.println("=====================");
            System.out.println("1. Add faculty data");
            System.out.println("2. Add Department data");
            System.out.println("3. View Faculty data");
            System.out.println("4. View department data");
            System.out.println("5. Exit");
            System.out.println("=====================");
            System.out.print("Choice : ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1){
                System.out.print("ID : ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Code : ");
                String code = sc.next();
                sc.nextLine();
                System.out.print("Name : ");
                String name = sc.nextLine();
                admin.addFaculty(new Faculty(id,code,name));
            }
            else if (choice == 2){
                admin.viewFacultyData(null);
                System.out.print("ID : ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Code : ");
                String code = sc.next();
                sc.nextLine();
                System.out.print("Name : ");
                String name = sc.nextLine();
                System.out.print("Faculty ID : ");
                int facultyId = sc.nextInt();
                sc.nextLine();
                admin.addDepartmentData(facultyId,new Department(id,code,name,maranatha.getFaculty(facultyId)));

            }
            else if (choice == 3){
                System.out.print("Show all ? [Y]es [N]o : ");
                String showAll = sc.next();
                sc.nextLine();
                if (showAll.toUpperCase().equals("Y")) {
                    admin.viewFacultyData(null);
                }else if (showAll.toUpperCase().equals("N")){
                    System.out.print("Faculty ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Faculty Name : "+maranatha.getFaculty(id).getName());
                    System.out.println("Faculty Code : "+maranatha.getFaculty(id).getCode());
                    admin.viewFacultyData(id);
                }
            }
            else if (choice == 4){
                admin.viewFacultyData(null);
                System.out.print("Faculty ID : ");
                int facultyId = sc.nextInt();
                sc.nextLine();
                System.out.print("Show all ? [Y]es [N]o : ");
                String showAll = sc.next();
                sc.nextLine();
                if (showAll.toUpperCase().equals("Y")) {
                    admin.viewDepartmentData(facultyId,null);
                }else if (showAll.toUpperCase().equals("N")){
                    System.out.print("Department ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Department Name : "+maranatha.getFaculty(facultyId).getDepartment(id).getName());
                    System.out.println("Department Code : "+maranatha.getFaculty(facultyId).getDepartment(id).getCode());
                    System.out.println("Part of "+maranatha.getFaculty(7).getName()+" faculty");
                }
            }
        }while(choice!=5);
    }
}
