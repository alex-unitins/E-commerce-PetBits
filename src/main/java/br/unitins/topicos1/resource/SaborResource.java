package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.SaborDTO;
import br.unitins.topicos1.service.SaborService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/sabores")
public class SaborResource {
    
    @Inject
    public SaborService saborService;

    private static final Logger LOGGER = Logger.getLogger(SaborResource.class);

    @GET

    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Finding sabor by id: " + id);
        return Response.ok(saborService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Finding all sabores");
        return Response.ok(saborService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Finding sabor by name: " + nome);
        return Response.ok(saborService.findByNome(nome)).build();
    }

    @POST
    public Response create(SaborDTO dto) {
        LOGGER.info("Creating sabor");
        return Response.status(Status.CREATED).entity(saborService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, SaborDTO dto) {
        LOGGER.info("Updating sabor: " + dto);
        saborService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Deleting sabor by id: " + id);
        saborService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


}
