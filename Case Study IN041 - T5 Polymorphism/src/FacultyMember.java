import java.util.ArrayList;
import java.util.List;

public class FacultyMember {
    private List<Person> member;

    public FacultyMember(){
        member = new ArrayList<>();
    }
    public void showAll(){
        member.forEach(Person::writeOutput);
    }
    public boolean addPerson(Person person){
        return this.member.add(person);
    }
    public void showAllLecturer(){
        member.forEach(Person ->{
            if (Person instanceof Lecturer){
                System.out.println(((Lecturer)Person).getName());
                System.out.println(((Lecturer)Person).getNidn());
            }
        });
    }
}
