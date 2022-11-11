public class Lecturer extends Person {
    private String nidn;
    public Lecturer(){

    }

    public Lecturer(String name, String nidn) {
        super(name);
        this.nidn = nidn;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }
    public void writeOutput(){
        System.out.println("This is lecturer");
        System.out.println("Name: "+getName());
        System.out.println("Nidn: "+getNidn());
    }
}
