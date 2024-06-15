package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.RemedioDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.RemedioFileServiceImpl;
import br.unitins.topicos1.service.RemedioService;
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
@Path("/remedios")
public class RemedioResource {
    
    @Inject
    public RemedioService remedioService;

    @Inject
    public RemedioFileServiceImpl fileService;
    private static final Logger LOGGER = Logger.getLogger(RemedioResource.class);
    @GET

    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Finding remedio by id: " + id);
        return Response.ok(remedioService.findById(id)).build();
    }
    @GET
    @Path("/search/animal/{animal}")
    public Response findByAnimal(@PathParam("animal") String animal) {
        LOGGER.info("Finding remedio by animal: " + animal);
        return Response.ok(remedioService.findByAnimal(animal)).build();
    }
    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        LOGGER.info("Finding remedio by descricao: " + descricao);
        return Response.ok(remedioService.findByDescricao(descricao)).build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Finding all remedios");
        return Response.ok(remedioService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Finding remedio by name: " + nome);
        return Response.ok(remedioService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/marca/{marca}")
    public Response findByIdMarca(@PathParam("marca") Long idMarca) {
        LOGGER.info("Finding remedio by marca: " + idMarca);
        return Response.ok(remedioService.findByIdMarca(idMarca)).build();
    }
    @GET
    @Path("/search/idade/{idade}")
    public Response findByIdade(@PathParam("idade") Integer idade){
        LOGGER.info("Finding remedio by idade: " + idade);
        return Response.ok(remedioService.findByIdade(idade)).build();
    }
    @GET
    @Path("/search/pesoAnimal/{pesoAnimal}")
    
    public Response findByPesoAnimal(@PathParam("pesoAnimal") Integer pesoAnimal){
        LOGGER.info("Finding remedio by pesoAnimal: " + pesoAnimal);
        return Response.ok(remedioService.findByPesoAnimal(pesoAnimal)).build();
    }
    @POST
    @RolesAllowed("Admin")
    public Response create(RemedioDTO dto) {
        LOGGER.info("Creating remedio: " + dto);
        return Response.status(Status.CREATED).entity(remedioService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, RemedioDTO dto) {
        LOGGER.info("Updating remedio: " + dto);
        remedioService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Deleting remedio by id: " + id);
        remedioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    @PATCH
    @Path("/{id}/image/upload")
    @RolesAllowed("Admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOGGER.info("Uploading image for remedio: " + id);
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
