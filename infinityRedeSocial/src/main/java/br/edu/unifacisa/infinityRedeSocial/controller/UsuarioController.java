package br.edu.unifacisa.infinityRedeSocial.controller;

import br.edu.unifacisa.infinityRedeSocial.model.Usuario;
import br.edu.unifacisa.infinityRedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/adicionar_usuario")
    public String adicionarUsuarioForm() {
        return "adicionar_usuario";
    }
    @PostMapping("/adicionar_usuario")
    public String adicionarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String password, Model model) {
        // Verificar se o email já está em uso
        if (usuarioRepository.existsByEmail(email)) {
            // Email já em uso, adicione uma mensagem de erro ao modelo
            model.addAttribute("error", "O e-mail já está cadastrado. Por favor, escolha outro e-mail.");
            // Redirecione para a tela de adicionar usuário
            return "adicionar_usuario";
        }

        // Se o e-mail não estiver em uso, continue com o processo de adição de usuário

        // Criar um novo usuário e salvar no banco de dados
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarioRepository.save(usuario);

        // Redirecione para a página apropriada após adicionar o usuário (por exemplo, dashboard)
        return "redirect:/login";
    }

    @GetMapping("/listar_usuario")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listar_usuario";
    }
}