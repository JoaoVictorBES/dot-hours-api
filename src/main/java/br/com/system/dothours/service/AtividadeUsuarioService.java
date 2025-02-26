package br.com.system.dothours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.repository.AtividadeUsuarioRepository;


@Service
public class AtividadeUsuarioService {

    @Autowired
    private AtividadeUsuarioRepository atividadeUsuarioRepository;

}
