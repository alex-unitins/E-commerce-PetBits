package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.RemedioDTO;
import br.unitins.topicos1.service.RemedioService;
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
@Path("/remedios")
public class RemedioResource {
    
    @Inject
    public RemedioService remedioService;

    @GET

    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(remedioService.findById(id)).build();
    }
    @GET
    @Path("/search/animal/{animal}")
    public Response findByAnimal(@PathParam("animal") String animal) {
        return Response.ok(remedioService.findByAnimal(animal)).build();
    }
    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        return Response.ok(remedioService.findByDescricao(descricao)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(remedioService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(remedioService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/marca/{marca}")
    public Response findByIdMarca(@PathParam("marca") Long idMarca) {
        return Response.ok(remedioService.findByIdMarca(idMarca)).build();
    }
    @GET
    @Path("/search/idade/{idade}")
    public Response findByIdade(@PathParam("idade") Integer idade){
        return Response.ok(remedioService.findByIdade(idade)).build();
    }
    @GET
    @Path("/search/pesoAnimal/{pesoAnimal}")
    
    public Response findByPesoAnimal(@PathParam("pesoAnimal") Integer pesoAnimal){
        return Response.ok(remedioService.findByPesoAnimal(pesoAnimal)).build();
    }
    @POST
    @RolesAllowed("Admin")
    public Response create(RemedioDTO dto) {
        return Response.status(Status.CREATED).entity(remedioService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, RemedioDTO dto) {
        remedioService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        remedioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
