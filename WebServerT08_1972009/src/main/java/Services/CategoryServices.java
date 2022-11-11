package Services;

import Dao.CategoryDaoHibernate;
import Entity.CategoryEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categoryservices")
public class CategoryServices {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(){
        List<CategoryEntity> categoryEntityList = new CategoryDaoHibernate().getAll();
        return Response.status(200).entity(categoryEntityList).build();
    }

    @POST
    @Consumes
    public Response addCategory(CategoryEntity categoryEntity){
        CategoryEntity categoryEntity1 = categoryEntity;
        CategoryDaoHibernate categoryDaoHibernate = new CategoryDaoHibernate();
        categoryDaoHibernate.addData(categoryEntity1);
        return Response.status(200).entity(categoryEntity).build();

    }
}
