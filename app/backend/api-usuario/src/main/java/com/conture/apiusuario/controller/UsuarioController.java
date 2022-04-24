package com.conture.apiusuario.controller;

import com.conture.apiusuario.entity.SituacaoAtual;
import com.conture.apiusuario.entity.TipoReporte;
import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.requisicao.UsuarioLogin;
import com.conture.apiusuario.resposta.UsuarioLogado;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private SituacaoAtualRepository situacaoAtualRepository;

    @Autowired
    private TipoReporteRepository tipoReporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/situacao-atual")
    public ResponseEntity adicionarSituacaoAtual(@RequestBody @Valid SituacaoAtual novaSituacao){
        situacaoAtualRepository.save(novaSituacao);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/reporte")
    public ResponseEntity adicionarTipoReporte(@RequestBody @Valid TipoReporte novoTipoReporte){
        tipoReporteRepository.save(novoTipoReporte);
        return ResponseEntity.status(201).build();
    }

    @PostMapping
    public ResponseEntity adicionarUsuario(@RequestBody @Valid Usuario novoUsuario){
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity listarUsuarios() {
        if (GerenciadorUsuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(GerenciadorUsuario.listarUsuariosLogados());
    }

    @PostMapping("/logar")
    public ResponseEntity logar(@RequestBody UsuarioLogin usuario){
        Usuario usuarioLogado = usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getEmail());

        if(Objects.isNull(usuarioLogado)) {
            return ResponseEntity.status(404).build();
        }

        //GerenciadorUsuario.login(usuarioLogado);

        return ResponseEntity.status(201).body(usuarioLogado);
    }

}
