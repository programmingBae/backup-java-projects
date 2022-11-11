/**
 * @author AbednegoSteven - 1972009
 */
public class Faculty {
    private int id;
    private String code;
    private String name;
    private Department[] departments;

    public Faculty(){
        departments = new Department[0];
    }

    public Faculty(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
        departments = new Department[0];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department[] getDepartments(){
        return departments;
    }
    public void setDepartments(Department[] departments){
        this.departments = departments;
    }
    public Department getDepartment(int departmentId){
        for(Department i : departments ){
            if (i.getId() == departmentId){
                return i;
            }
        }
        return null;
    }
    public void showDepartmentData(Integer departmentId){
        if (departments.length==0){
            System.out.println("No Data of Department");
        }else if(departments.length!=0 && departmentId==null){
            System.out.printf("%10s%10s%20s", "ID", "Code", "Name");
            System.out.println();
            for(Department i : departments){
                System.out.printf("%10s%10s%20s", i.getId(),i.getCode(),i.getName());
                System.out.println();
            }
        }else{
            System.out.printf("%10s%10s%20s", "ID", "Code", "Name");
            System.out.println();
            for (Department i : departments){
                if (i.getId() == departmentId){
                    System.out.printf("%10s%10s%30s",i.getId(),i.getCode(),i.getName());
                }
            }
        }

    }



}
