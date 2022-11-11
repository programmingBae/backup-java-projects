import java.util.Arrays;

/**
 * @author AbednegoSteven - 1972009
 */
public class Admin extends User {
    private University university;

    public void setUniversity(University university){
        this.university = university;
    }
    public void addFaculty(Faculty faculty){
        boolean duplicate = false;
        for (Faculty i : university.getFaculties()){
            if(i.getId() == faculty.getId() || i.getCode() == faculty.getCode()){
                duplicate = true;
                break;
            }

        }
        if (duplicate){
            System.out.println("ID or Code already exists");
        }
        else{
            university.setFaculties(Arrays.copyOf(university.getFaculties(), university.getFaculties().length+1));
            university.getFaculties()[university.getFaculties().length-1] = faculty;
        }
    }
    public void addDepartmentData(int facultyId, Department department){
        boolean duplicate = false;
        Faculty faculty = university.getFaculty(facultyId);
        if (faculty == null){
            System.out.println("Faculty not found");
        }
        else{
            for (Department i : faculty.getDepartments()){
                if(i.getId() == department.getId() || i.getCode() == department.getCode()){
                    duplicate = true;
                    break;
                }
            }
            if (duplicate){
                System.out.println("ID or Code already exists");
            }
            else{
                faculty.setDepartments(Arrays.copyOf(faculty.getDepartments(),faculty.getDepartments().length+1));
                faculty.getDepartments()[faculty.getDepartments().length-1] = department;
            }
        }
    }
    public void viewFacultyData(Integer facultyId){
        if (facultyId == null){
            university.showFacultyData(null);
        }else if(university.getFaculty(facultyId) != null){
            university.showFacultyData(facultyId);
        }else{
            System.out.println("Faculty not found");
        }

    }
    public void viewDepartmentData(int facultyId,Integer index){
        Faculty faculty = university.getFaculty(facultyId);
        if(faculty!=null){
            faculty.showDepartmentData(index);
        }
        else{
            System.out.println("Faculty not found");
        }

    }

    @Override
    public boolean login() {
        return username.equals("Abednego") && password.equals("1972009");
    }
}
