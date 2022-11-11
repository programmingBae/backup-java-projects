import java.util.Scanner;

public class ShapeDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();
        Scanner sc = new Scanner (System.in);
        int choice;
        do{
            System.out.println("================================");
            System.out.println("1. Rectangle");
            System.out.println("2. Circle");
            System.out.println("3. Exit");
            System.out.print("Choice : ");
            choice = sc.nextInt();
            if (choice == 1){
                System.out.print("Length : ");
                double length = sc.nextDouble();
                System.out.print("Width : ");
                double width = sc.nextDouble();
                rectangle.setLength(length);
                rectangle.setWidth(width);
                System.out.println("Area : "+rectangle.getArea());
            }
            else if(choice == 2){
                System.out.print("Radius : ");
                double radius = sc.nextDouble();
                circle.setRadius(radius);
                System.out.println("Area : "+circle.getArea());
            }

        }while(choice != 3);
    }
}
