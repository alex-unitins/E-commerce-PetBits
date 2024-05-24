package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.AdminDTO;
import br.unitins.topicos1.dto.AdminResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Admin;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.AdminRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {

    @Inject
    public AdminRepository adminRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public AdminResponseDTO create(@Valid AdminDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        // gerando o hash da senha
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        // salvando o usuario
        usuarioRepository.persist(usuario);
      
        Admin admin = new Admin();
        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        admin.setCargo(dto.cargo());
        admin.setSalario(dto.salario());
        admin.setListaTelefone(new ArrayList<Telefone>());
        admin.setUsuario(usuario);

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            admin.getListaTelefone().add(t);
        }

        adminRepository.persist(admin);

        return AdminResponseDTO.valueOf(admin);
    }

    @Override
    @Transactional
    public void update(Long id, AdminDTO dto) {
        Admin adminBanco =  adminRepository.findById(id);

        adminBanco.setNome(dto.nome());
        adminBanco.setEmail(dto.email());
        adminBanco.setCargo(dto.cargo());
        adminBanco.setSalario(dto.salario());
        adminBanco.getListaTelefone().clear();

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            adminBanco.getListaTelefone().add(t);
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminResponseDTO findById(Long id) {
        return AdminResponseDTO.valueOf(adminRepository.findById(id));
    }

    @Override
    public List<AdminResponseDTO> findAll() {
        return adminRepository
        .listAll()
        .stream()
        .map(e -> AdminResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AdminResponseDTO> findByNome(String nome) {
        return adminRepository.findByNome(nome).stream()
        .map(e -> AdminResponseDTO.valueOf(e)).toList();
   }

    public UsuarioResponseDTO login(String username, String senha) {
        Admin admin = adminRepository.findByUsernameAndSenha(username, senha);
        return UsuarioResponseDTO.valueOfAdmin(admin);
    }

}