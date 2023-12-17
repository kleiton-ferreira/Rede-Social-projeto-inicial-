package br.edu.unifacisa.infinityRedeSocial.controller;

import br.edu.unifacisa.infinityRedeSocial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        if (email.isEmpty() || password.isEmpty()) {
            // Campos em branco, adicione uma mensagem de erro ao modelo
            model.addAttribute("error", "Por favor, preencha todos os campos.");
            // Redirecione de volta para a página de login
            return "login";
        }

        if (usuarioService.validarCredenciais(email, password)) {
            // Credenciais válidas, redirecione para a página apropriada (por exemplo, dashboard)
            return "redirect:/nova_postagem";
        } else {
            // Credenciais inválidas, adicione uma mensagem de erro ao modelo
            model.addAttribute("error", "Login Inválido!");
            // Redirecione de volta para a página de login
            return "login";
        }
    }
}
