package org.bitxbit;

import org.bitxbit.model.BeaconEvent;
import org.bitxbit.model.Tweet;
import org.bitxbit.model.BeaconEventDao;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.net.URI.create;

@Path("beacon")
public class BeaconEventResource {
    @GET
    @Path("/visits")
    @Produces(MediaType.APPLICATION_JSON)
    public int getVisits() {
        BeaconEventDao dao = new BeaconEventDao();
        return dao.getNumVisits();
    }

    @POST
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setBeaconEvent(InputStream is) {
        try {
            BeaconEvent event = new ObjectMapper().readValue(is, BeaconEvent.class);
            long id = new BeaconEventDao().setBeaconEvent(event);
            return Response.created(create("/id/" + id)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
