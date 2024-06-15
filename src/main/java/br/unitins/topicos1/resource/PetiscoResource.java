package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.PetiscoDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.PetiscoFileServiceImpl;
import br.unitins.topicos1.service.PetiscoService;
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
@Path("/petiscos")
public class PetiscoResource {
    
    @Inject
    public PetiscoService petiscoService;

    @Inject
    public PetiscoFileServiceImpl fileService;

    private static final Logger LOGGER = Logger.getLogger(PetiscoResource.class);

    @GET
    @Path("/search/animal/{animal}")
    public Response findByAnimal(@PathParam("animal") String animal) {
        LOGGER.info("Finding petisco by animal: " + animal);
        return Response.ok(petiscoService.findByAnimal(animal)).build();
    }
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Finding petisco by id: " + id);
        return Response.ok(petiscoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Finding all petiscos");
        return Response.ok(petiscoService.findAll()).build();
    }
    @GET
    @Path("/search/pesoProduto/{pesoProduto}")
    public Response findByPesoProduto(@PathParam ("pesoProduto")Integer idPesoProduto){
        LOGGER.info("Finding petisco by pesoProduto: " + idPesoProduto);
        return Response.ok(petiscoService.findByPesoProduto(idPesoProduto)).build();
    }
    @GET 
    @Path("/search/sabor/{sabor}")
        public Response findBySabor(@PathParam("sabor") Long sabor){
            LOGGER.info("Finding petisco by sabor: " + sabor);
            return Response.ok(petiscoService.findBySabor(sabor)).build();
        }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Finding petisco by name: " + nome);
        return Response.ok(petiscoService.findByNome(nome)).build();
    }
    @GET
    @Path("/search/marca/{marca}")
    public Response findByIdMarca(@PathParam("marca") Long idMarca) {
        LOGGER.info("Finding petisco by marca: " + idMarca);
        return Response.ok(petiscoService.findByIdMarca(idMarca)).build();
    }
    @POST
    @RolesAllowed("Admin")
    public Response create(PetiscoDTO dto) {
        LOGGER.info("Creating petisco: " + dto);
        return Response.status(Status.CREATED).entity(petiscoService.create(dto)).build();
    }
    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        LOGGER.info("Finding petisco by descricao: " + descricao);
        return Response.ok(petiscoService.findByDescricao(descricao)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, PetiscoDTO dto) {
        LOGGER.info("Updating petisco: " + dto);
        petiscoService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Deleting petisco by id: " + id);
        petiscoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    @PATCH
    @Path("/{id}/image/upload")
    @RolesAllowed("Admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOGGER.info("Uploading image for petisco: " + id);
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
