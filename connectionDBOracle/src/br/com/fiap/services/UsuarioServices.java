package br.com.fiap.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioDAO;

public class UsuarioServices {
private UsuarioDAO usuariodao;

public UsuarioServices(Connection connection) {
	this.usuariodao = new UsuarioDAO(connection);
}


public void cadastrarUsuario(Usuario usuario) throws SQLException {
    usuariodao.insert(usuario);
}

// Método para listar todos os usuários
public List<Usuario> listarUsuarios() throws SQLException {
    return usuariodao.selectAll();
}

// Método para buscar um usuário pelo ID
public Usuario buscarUsuarioPorId(long id) throws SQLException {
    return usuariodao.selectById(id);
}

// Método para atualizar um usuário
public void atualizarUsuario(Usuario usuario) throws SQLException {
    usuariodao.update(usuario);
}

// Método para excluir um usuário
public void excluirUsuario(long id) throws SQLException {
    usuariodao.delete(id);
}
}