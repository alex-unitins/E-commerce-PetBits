package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.AdminDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.AdminService;
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
@Path("/admins")

public class AdminResource {

    @Inject
    public AdminService adminService;

    @GET
    @RolesAllowed("Admin")
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(adminService.findById(id)).build();
    }
    @RolesAllowed("Admin")
    @GET
    public Response findAll() {
        return Response.ok(adminService.findAll()).build();
    }
    @RolesAllowed("Admin")
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
         return Response.ok(adminService.findByNome(nome)).build();
    }
    @RolesAllowed("Admin")
    @POST
    public Response create(AdminDTO dto) {
        return Response.status(Status.CREATED).entity(adminService.create(dto)).build();
    }

    
    @RolesAllowed("Admin")
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        adminService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    @PATCH
    @RolesAllowed("Admin")
    @Path("/updateUsername/{username}")
    public Response updateUsername(@PathParam("username") String username) {
        return adminService.updateUsername(username);
    }
    @PATCH
    @RolesAllowed("Admin")
    @Path("/updateSenha/{senhaAntiga}/{novaSenha}")
    public Response updateSenha(@PathParam("senhaAntiga") String senhaAntiga, @PathParam("novaSenha") String novaSenha) {
        return adminService.updateSenha(senhaAntiga, novaSenha);
    }   
    @PATCH
    @RolesAllowed("Admin")
    @Path("/updateEmail/{email}")
    public Response updateEmail(@PathParam("email") String email) {
        return adminService.updateEmail(email);
    }  
    @PATCH
    @RolesAllowed("Admin")
    @Path("/updateListaTelefone")
    public Response updateListaTelefone(List<TelefoneDTO> listaTelefone) {
        return adminService.updateListaTelefone(listaTelefone);
    }
    

}
