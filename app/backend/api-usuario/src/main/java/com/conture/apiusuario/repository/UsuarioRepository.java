package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.resposta.UsuarioLogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /*@Query("select new com.conture.apiusuario.resposta.UsuarioLogado(u.id_usuario, u.nome, u.sobrenome, u.genero," +
            "u.data_nascimento, u.estado_civil, u.cep, u.data_cadastro, u.escolaridade, u.fk_situacao_atual" +
            "from Usuario u")*/
    // List<UsuarioLogado> listaUsuario();

    Usuario findByEmailAndSenha(String email, String senha);

    //@Query("select new com.conture.apiusuario.resposta.UsuarioLogado u where u.email = ?1 and u.senha = ?2")
    //UsuarioLogado econtrarUsuario(String email, String senha);
}
