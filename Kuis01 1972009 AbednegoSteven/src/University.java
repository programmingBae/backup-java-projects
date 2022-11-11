/**
 * @author AbednegoSteven - 1972009
 */
public class University {
    private String name;
    private String address;
    private Faculty[] faculties;

    public University(){
        faculties = new Faculty[0];
    }

    public University(String name, String address) {
        this.name = name;
        this.address = address;
        faculties = new Faculty[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Faculty[] getFaculties(){
        return faculties;
    }
    public void setFaculties(Faculty[] faculties){
        this.faculties = faculties;
    }
    public Faculty getFaculty (int facultyId){
        for(Faculty i : faculties ){
            if (i.getId() == facultyId){
                return i;
            }
        }
        return null;
    }
    public void showFacultyData(Integer facultyId){
        if (faculties.length==0){
            System.out.println("No Data of Faculty");
        }else if(faculties.length!=0 && facultyId==null){
            System.out.printf("%10s%10s%20s", "ID", "Code", "Name");
            System.out.println();
            for(Faculty i : faculties){
                System.out.printf("%10s%10s%20s", i.getId(),i.getCode(),i.getName());
                System.out.println();
            }
        }else{
            System.out.printf("%10s%10s%20s", "ID", "Code", "Name");
            System.out.println();
            for (Faculty i : faculties){
                if (i.getId() == facultyId){
                    System.out.printf("%10s%10s%20s",i.getId(),i.getCode(),i.getName());
                }
            }
        }

    }

}
