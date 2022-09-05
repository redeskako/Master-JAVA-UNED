package pro.jbelo.services;

import com.sun.istack.internal.NotNull;
import pro.jbelo.persistence.DAOImplementation;
import pro.jbelo.persistence.DataEntity;
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
@Path("data")
@ApplicationScoped
public class DataService {

    @Inject
    DAOImplementation dao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCountry(@NotNull DataEntity dataEntity){
        try {
            dao.createData(dataEntity);
            return Response.status(Response.Status.CREATED).build();
        }catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCountry(
            @QueryParam("indicatorCode") @NotNull String indicatorCode,
            @QueryParam("countryCode") @NotNull String countryCode,
            @QueryParam("year") @NotNull int year){
        try {
            DataEntity dataEntity = dao.readData(indicatorCode, countryCode, year);
            if(dataEntity != null){
                return Response.ok(dataEntity).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (ServerRunningException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readData(){
        List<DataEntity> dataEntities = null;
        try {
            dataEntities = dao.readData();
            if(dataEntities != null) {
                return Response.ok(dataEntities).build();
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
    public Response updateData(@NotNull DataEntity dataEntity){
        try{
            dao.updateData(dataEntity);
            return Response.status(Response.Status.OK).build();
        } catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @DELETE
    public Response deleteData(
            @QueryParam("indicatorCode") @NotNull String indicatorCode,
            @QueryParam("countryCode") @NotNull String countryCode,
            @QueryParam("year") @NotNull int year){

        try{
            // TODO: move these two steps to DAO to use transaction
            dao.deleteData(indicatorCode, countryCode, year);
            return Response.status(Response.Status.OK).build();
        } catch (ServerRunningException e ){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }
}

