package Service;

import Model.CategoryEntity;
import Model.Student;
import dao.CategoryDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/test")
public class TestService {
    //@Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTest(){
       /* Student student = new Student(1,"Abed");
        return Response.status(200).entity(student).build();*/

        List<CategoryEntity> categoryEntityList = new CategoryDao().getAll();

        if (categoryEntityList!=null){
        return Response.status(200).entity(categoryEntityList).build();
        }
        else {
            Student student = new Student(1,"Abed");
            return Response.status(200).entity(student).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response kirimNilai(Student s2){
        Student student = s2;
        student.setName("anjay "+ student.getName());
        return Response.status(200).entity(student).build();
    }


}
