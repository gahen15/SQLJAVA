package br.com.fiap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

public class UsuarioDAO {

	private Connection connection;
	

	public UsuarioDAO(Connection connection) {
		this.connection = new ConnectionFactory().conectar();
	}

	// insert
	public void insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario (nome, email, dataCadastro) VALUES (?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setDate(3, usuario.getDataCadastro());

			stmt.execute();
			stmt.close();

		}

	}

	// delete
	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		}

	}

	// update
	public void update(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuario SET nome = ?, email = ?, dataCadastro = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setDate(3, usuario.getDataCadastro());
			stmt.setLong(4, usuario.getId());

			stmt.execute();
			stmt.close();

		}
	}

	// selectALL
	public List<Usuario> selectAll() throws SQLException {
		String sql = "SELECT * FROM usuario order by id";

		List<Usuario> usuarios = new ArrayList<Usuario>();

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDataCadastro(rs.getDate("dataCadastro"));
				usuarios.add(usuario);
			}
			stmt.close();
		}

	
		return usuarios;
	}

	// selectById
	
	// selectById
	public Usuario selectById(long id) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setDataCadastro(rs.getDate("dataCadastro"));
		
		
	stmt.close();
	}

}}
		return usuario;}}