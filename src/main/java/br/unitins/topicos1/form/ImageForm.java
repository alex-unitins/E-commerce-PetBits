package br.unitins.topicos1.form;


import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class ImageForm {
    
    @FormParam("nomeImagem")
    @PartType(MediaType.TEXT_PLAIN)
    private String nomeImagem;

    @FormParam("Imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    public String getNomeImagem(){
        return nomeImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
