package pro.jbelo.services;

import com.sun.istack.internal.NotNull;
import pro.jbelo.persistence.CountryEntity;
import pro.jbelo.persistence.DAOImplementation;
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
@Path("countries")
@ApplicationScoped
public class CountryService {

    @Inject
    DAOImplementation dao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCountry(@NotNull CountryEntity countryEntity){
        System.out.println("entra en createCountry");
        try {
            if(countryEntity != null){
                System.out.println(countryEntity.getCountryName());
                System.out.println(countryEntity.getCountryName());
            }
            dao.createCountry(countryEntity);
            return Response.status(Response.Status.CREATED).build();
        }catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @GET
    @Path("country")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCountry(@QueryParam("id") @NotNull String id){
        try {
            CountryEntity countryEntity = dao.readCountry(id);
            if(countryEntity != null){
                return Response.ok(countryEntity).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (ServerRunningException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }


    @GET
    @Path("countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCountries(){
        List<CountryEntity> countryEntities = null;
        try {
            countryEntities = dao.readCountries();
            if(countryEntities != null) {
                return Response.ok(countryEntities).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (ServerRunningException e) {
            System.out.println("es una exception");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCountry(@NotNull CountryEntity countryEntity){
        try{
            dao.updateCountry(countryEntity);
            return Response.status(Response.Status.OK).build();
        } catch (ServerRunningException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCountry(@PathParam("id") @NotNull String id){
        List<CountryEntity> countryEntities = null;
        System.out.println("enra en delete");
        try{
            // TODO: manage situation when we delete all countries and will return null
            countryEntities = dao.deleteCountry(id);
            if(countryEntities == null) {
                System.out.println("es NULL NULL");
            } else {
                System.out.println("tamaño devuelto es " + countryEntities.size());
            }
            return Response.ok(countryEntities).build();
        } catch (ServerRunningException e ){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString()).build();
        }
    }

}
