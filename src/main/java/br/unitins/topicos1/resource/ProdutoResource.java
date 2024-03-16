package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    public ProdutoService service;

    @Inject
    Validator validator;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @POST
    public Response create(@Valid ProdutoDTO dto) {
        try {
            return Response.status(Status.CREATED).entity(service.create(dto)).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST).entity(getConstraintViolationMessages(e)).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid ProdutoDTO dto) {
        try {
            service.update(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST).entity(getConstraintViolationMessages(e)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/nome/{descricao}")
    public Response findByNome(@PathParam("descricao") String descricao) {
        return Response.ok(service.findByNome(descricao)).build();
    }

    private String getConstraintViolationMessages(ConstraintViolationException e) {
        StringBuilder messages = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            messages.append(violation.getMessage()).append(", ");
        }
        return messages.toString();
    }
}
