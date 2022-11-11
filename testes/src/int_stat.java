import java.util.Scanner;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

public class int_stat {
    private java.util.List<Integer> values = new ArrayList<Integer>();

    public void Add(int value) {
        this.values.add(value);
    }

    public Integer Sum() {
        int a = 0;
        for (int i = 0; i<this.values.size(); i++) {
            a += this.values.get(i);
        }
        return a;
    }

    public Integer Count() {
        return this.values.size();
    }

    public Double Average() {
        if (this.values.size() <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int tes = this.Sum() / this.Count();
        return Double.parseDouble(String.valueOf(tes));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println();
            int_stat s = new int_stat();
            System.out.print("Number of Data: ");
            int num = sc.nextInt();

            for (int i = 0; i < num; i++){
                System.out.print("Data- "+ (i+1) + ": ");
                int data = sc.nextInt();
                s.Add(data);
            }
            System.out.println();
            System.out.println("Data = " + s.values);
            System.out.println("Sum = " + s.Sum());
            System.out.println("Average= "+s.Average());
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.print("failed to compute average: devide by zero");
        }
        catch(Exception ex){
            System.out.print("failed to get input: expected integer");
        }

    }
}