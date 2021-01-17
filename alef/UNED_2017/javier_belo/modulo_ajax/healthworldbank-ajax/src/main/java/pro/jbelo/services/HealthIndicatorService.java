package pro.jbelo.services;

import com.sun.istack.internal.NotNull;
import pro.jbelo.persistence.DAOImplementation;
import pro.jbelo.persistence.HealthIndicatorEntity;
import pro.jbelo.utils.ServerRunningException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author jbelo
 * @date 2017 07.
 */
@Path("healthindicator")
@ApplicationScoped
public class HealthIndicatorService {

    @Inject
    DAOImplementation dao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHealthIndicator(@NotNull HealthIndicatorEntity healthIndicatorEntity){
        try {
            dao.createHealthIndicator(healthIndicatorEntity);
            return Response.status(Response.Status.CREATED).build();
        }catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @GET
    @Path("indicator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHealthIndicator(@QueryParam("id") @NotNull String id){
        try {
            HealthIndicatorEntity healthIndicatorEntity = dao.readHealthIndicator(id);
            if(healthIndicatorEntity != null){
                return Response.ok(healthIndicatorEntity).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (ServerRunningException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @GET
    @Path("indicators")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHealthIndicators(){
        List<HealthIndicatorEntity> indicatorEntities = null;
        try {
            indicatorEntities = dao.readHealthIndicators();
            if(indicatorEntities != null) {
                return Response.ok(indicatorEntities).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (ServerRunningException e) {
            System.out.println("es una exception");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateHealthIndicator(@NotNull HealthIndicatorEntity healthIndicatorEntity){
        try{
            dao.updateHealthIndicator(healthIndicatorEntity);
            return Response.status(Response.Status.OK).build();
        } catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteHealthIndicator(@PathParam("id") @NotNull String id){

        try{
            // TODO: move these two steps to DAO to use transaction
            dao.deleteHealthIndicator(id);
            return Response.status(Response.Status.OK).build();
        } catch (ServerRunningException e ){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

}
