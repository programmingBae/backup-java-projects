package sample;

public class Student {
    private int NRP;
    private String firstName;
    private String lastName;
    private String phone;

    public Student() {
    }

    public int getNRP() {
        return NRP;
    }

    public void setNRP(int NRP) {
        this.NRP = NRP;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
