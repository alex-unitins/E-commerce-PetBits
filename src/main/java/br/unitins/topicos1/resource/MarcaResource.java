package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        MarcaResponseDTO marca = service.findById(id);
        if (marca != null) {
            return Response.ok(marca).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @POST
    public Response create(@Valid MarcaDTO dto) {
        MarcaResponseDTO createdMarca = service.create(dto);
        return Response.status(Status.CREATED).entity(createdMarca).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid MarcaDTO dto) {
        if (service.findById(id) != null) {
            service.update(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }
}
