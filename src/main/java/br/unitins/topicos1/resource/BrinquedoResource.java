package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.BrinquedoDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.BrinquedoFileServiceImpl;
import br.unitins.topicos1.service.BrinquedoService;
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
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/brinquedos")
public class BrinquedoResource {
    
    @Inject
    public BrinquedoService brinquedoService;

    @Inject
    public BrinquedoFileServiceImpl fileService; 

    private static final Logger LOGGER = Logger.getLogger(BrinquedoResource.class);

    @GET

    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Finding brinquedo by id: " + id);
        return Response.ok(brinquedoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Finding all brinquedos");
        return Response.ok(brinquedoService.findAll()).build();
    }
    @GET
    @Path("/search/tipoBrinquedo/{tipoBrinquedo}")
    public Response findoByIdTipoBrinquedo(@PathParam("tipoBrinquedo") Integer idTipo){
        LOGGER.info("Finding brinquedo by tipoBrinquedo: " + idTipo);
        return Response.ok(brinquedoService.findByIdTipoBrinquedo(idTipo)).build();
    }
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Finding brinquedo by name: " + nome);
        return Response.ok(brinquedoService.findByNome(nome)).build();
    }
    @GET
    @Path("/search/animal/{animal}")
    public Response findByAnimal(@PathParam("animal") String animal) {
        LOGGER.info("Finding brinquedo by animal: " + animal);
        return Response.ok(brinquedoService.findByAnimal(animal)).build();
    }
    @GET
    @Path("/search/marca/{marca}")
    public Response findByIdMarca(@PathParam("marca") Long idMarca) {
        LOGGER.info("Finding brinquedo by marca: " + idMarca);
        return Response.ok(brinquedoService.findByIdMarca(idMarca)).build();
    }
    
    @POST
    @RolesAllowed("Admin")
    public Response create(BrinquedoDTO dto) {
        LOGGER.info("Creating brinquedo: " + dto);
        return Response.status(Status.CREATED).entity(brinquedoService.create(dto)).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        LOGGER.info("Finding brinquedo by descricao: " + descricao);
        return Response.ok(brinquedoService.findByDescricao(descricao)).build();
    }

    @PUT
    @RolesAllowed("Admin")
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, BrinquedoDTO dto) {
        LOGGER.info("Updating brinquedo: " + dto);
        brinquedoService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Deleting brinquedo with ID: " + id);
        brinquedoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    @PATCH
    @Path("/{id}/image/upload")
    @RolesAllowed("Admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOGGER.info("Uploading image: " + form.getNomeImagem());
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        LOGGER.info("Downloading image: " + nomeImagem);
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }   
    

}
