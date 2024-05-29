package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class PedidoResource {

    public PedidoService pedidoService;

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(pedidoService.findAll()).build();
    }

    @GET
    @Path("/search/cliente/{id}")
    public Response findByCliente(@PathParam("id") Long id) {
        return Response.ok(pedidoService.findByCliente(id)).build();
    }
    


    @POST
    @RolesAllowed("Admin")
    public Response create(PedidoDTO dto) {
        return Response.status(Status.CREATED).entity(pedidoService.create(dto)).build();
    }

}
