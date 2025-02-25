package br.com.system.dothours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.service.AtividadeUsuarioService;

@RestController
@RequestMapping("/atividade-usuario")
public class AtividadeUsuarioController {

    @Autowired
    private AtividadeUsuarioService atividadeUsuarioService;

    

}
