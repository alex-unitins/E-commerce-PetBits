package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaFileServiceImpl implements FileService {

   // ex. /user/janio/quarkus/images/usuario/
    private final String PATH_USER = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "images"
        + File.separator + "marca" + File.separator;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) {
        Marca marca = marcaRepository.findById(id);

        try {
            String novoNomeImagem = salvarImagem(imagem, nomeImagem);
            marca.setNomeImagem(novoNomeImagem);
            
        } catch (IOException e) {
            throw new ValidationException("imagem", e.toString());
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {
        
       
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada.");
        }

        
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        
        String nomeArquivo = UUID.randomUUID()
        +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeArquivo;

        
        File file = new File(path);
        
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
       
        fos.flush();
        fos.close();

        return nomeArquivo;
    
    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    } 
}