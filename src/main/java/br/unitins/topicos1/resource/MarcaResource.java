package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.MarcaFileServiceImpl;
import br.unitins.topicos1.service.MarcaService;
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
@Path("/marcas")
public class MarcaResource {
    
    @Inject
    public MarcaService marcaService;

    @Inject
    public MarcaFileServiceImpl fileService;
    private static final Logger LOGGER = Logger.getLogger(MarcaResource.class);
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Finding marca by id: " + id);
        return Response.ok(marcaService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Finding all marcas");
        return Response.ok(marcaService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Finding marca by name: " + nome);
        return Response.ok(marcaService.findByNome(nome)).build();
    }
    


    @POST
    @RolesAllowed("Admin")
    public Response create(MarcaDTO dto) {
        LOGGER.info("Creating marca");
        return Response.status(Status.CREATED).entity(marcaService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, MarcaDTO dto) {
        LOGGER.info("Updating marca: " + id);
        marcaService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Deleting marca by id: " + id);
        marcaService.delete(id);
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
