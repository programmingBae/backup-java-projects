package Services;

import Dao.ItemsDaoHibernate;
import Entity.ItemsEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/itemservices")
public class ItemsServices {
    ItemsDaoHibernate itemsDaoHibernate = new ItemsDaoHibernate();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems(){
        List<ItemsEntity> itemEntityList = new ItemsDaoHibernate().getAll();
        return Response.status(200).entity(itemEntityList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemsEntity itemsEntity){
        ItemsEntity itemsEntity1 = itemsEntity;
       itemsDaoHibernate.addData(itemsEntity1);
       return Response.status(200).entity(itemsEntity1).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(ItemsEntity itemsEntity){
        ItemsEntity itemsEntity1 = itemsEntity;
        itemsDaoHibernate.updateData(itemsEntity1);
        return Response.status(200).entity(itemsEntity1).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(ItemsEntity itemsEntity){
        ItemsEntity itemsEntity1 = itemsEntity;
        itemsDaoHibernate.delData(itemsEntity1);
        return Response.status(200).entity(itemsEntity1).build();
    }


}
