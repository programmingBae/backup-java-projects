package Services;


import Dao.ItemsDaoHibernate;
import Entity.ItemsEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/deleteitemservices")
public class DeleteItemServices {
    ItemsDaoHibernate itemsDaoHibernate = new ItemsDaoHibernate();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(ItemsEntity itemsEntity){
        ItemsEntity itemsEntity1 = itemsEntity;
        itemsDaoHibernate.delData(itemsEntity1);
        return Response.status(200).entity(itemsEntity1).build();
    }
}
