package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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
@Path("/clientes")
public class ClienteResource {
    
    @Inject
    public ClienteService clienteService;

    
    @GET
    @RolesAllowed("Admin")
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(clienteService.findById(id)).build();
    }
    
    @RolesAllowed("Admin")
    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll()).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
         return Response.ok(clienteService.findByNome(nome)).build();
    }
    
    @POST
    public Response create(ClienteDTO dto) {
        return Response.status(Status.CREATED).entity(clienteService.create(dto)).build();
    }

    
    @RolesAllowed("Admin")
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @PATCH
    @RolesAllowed("Cliente")
    @Path("/updateUsername/{username}")
    public Response updateUsername(@PathParam("username") String username) {
        
        return clienteService.updateUsername(username);
    }
    
    @PATCH
    @RolesAllowed("Cliente")
    @Path("/updateSenha/{senhaAntiga, novaSenha}")
    public Response updateSenha(@PathParam("senhaAntiga") String senhaAntiga, @PathParam("novaSenha") String novaSenha) {
        return clienteService.updateSenha(senhaAntiga, novaSenha);
    }
    
    @PATCH
    @RolesAllowed("Cliente")
    @Path("/updateEmail/{email}")
    public Response updateEmail(@PathParam("email") String email) {
        return clienteService.updateEmail(email);
    }
    
    @PATCH
    @RolesAllowed("Cliente")
    @Path("/updateListaTelefone")
    public Response updateListaTelefone(List<TelefoneDTO> listaTelefone) {
        return clienteService.updateListaTelefone(listaTelefone);
    }
    
    @PATCH
    @RolesAllowed("Cliente")
    @Path("/updateEndereco/{rua}/{numero}/{cidade}/{estado}/{cep}")
    public Response updateEndereco(@PathParam("rua") String rua, @PathParam("numero") String numero,  @PathParam("cidade") String cidade, @PathParam("estado") String estado, @PathParam("cep") String cep) {
        return clienteService.updateEndereco(rua, numero, cidade, estado, cep);
    }
}
