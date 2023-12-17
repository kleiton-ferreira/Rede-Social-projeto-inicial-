package br.edu.unifacisa.infinityRedeSocial.service;

import br.edu.unifacisa.infinityRedeSocial.model.Usuario;
import br.edu.unifacisa.infinityRedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarCredenciais(String email, String password) {
        // Busca o usuário pelo email (supondo que o email seja único)
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Verifica se o usuário existe e a senha está correta
        return usuario != null && usuario.getPassword().equals(password);
    }
}