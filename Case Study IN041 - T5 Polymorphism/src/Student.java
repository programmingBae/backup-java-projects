public class Student extends Person{
    private int studentNumber;
    public Student(){

    }

    public Student(String name, int studentNumber) {
        super(name);
        this.studentNumber = studentNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    public void writeOutput(){
        System.out.println("This is student");
        System.out.println("Name: "+getName());
        System.out.println("NRP: "+getStudentNumber());
    }
}
