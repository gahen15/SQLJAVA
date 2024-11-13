package br.com.fiap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.jdbc.models.Candidato;

public class CandidatoDAO {

	private Connection connection;

	public void insert(Candidato candidato) throws SQLException {

		String sql = "INSERT INTO t_candidato (nome, telefone, email, endereco, formacao, dataNascimento, tempoFormacao, genero, idArea) VALUES (?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getTelefone());
			stmt.setString(3, candidato.getEmail());
			stmt.setString(4, candidato.getEndereco());
			stmt.setString(5, candidato.getFormacao());
			stmt.setDate(6, candidato.getDataNasc());
			stmt.setFloat(7, candidato.getTempoExp());
			stmt.setString(8, candidato.getGenero());
			stmt.setLong(9, candidato.getIdArea());

			stmt.execute();
			stmt.close();

		}

	}

	public void delete(long id) throws SQLException {

		String sql = "DELETE FROM t_candidato WHERE idCandidato = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

		}

	}

	public void update(Candidato candidato) throws SQLException {
		String sql = "UPDATE t_candidato SET nome = ?, telefone = ?, email = ?, endereco = ?, formacao = ?, dataNascimento = ?, tempoFormacao = ?, genero = ?, idArea = ? WHERE idCandidato = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getTelefone());
			stmt.setString(3, candidato.getEmail());
			stmt.setString(4, candidato.getEndereco());
			stmt.setString(5, candidato.getFormacao());
			stmt.setDate(6, candidato.getDataNasc());
			stmt.setFloat(7, candidato.getTempoExp());
			stmt.setString(8, candidato.getGenero());
			stmt.setLong(9, candidato.getIdArea());
			stmt.setLong(9, candidato.getIdCandidato());

			stmt.execute();
			stmt.close();

		}

	}

	public List<Candidato> selectAll() throws SQLException{
		String slq = "SELECT * FROM t_candidato ORDER BY idCandidato";
		
		try(PreparedStatement stmt = connection.prepareStatement(slq); ResultSet rs = stmt.executeQuery()){
			
			while (rs.next()) {
				Candidato candidato = new Candidato();
				candidato.setNome(rs.getString("nome"));
				candidato.setTelefone(rs.getString("telefone"));
				candidato.setEmail(rs.getString("email"));
				candidato.setEndereco(rs.getString("endereco"));
				candidato.setFormacao(rs.getString("formacao"));
				candidato.setDataNasc(rs.getDate("dataNasc"));
				candidato.setTempoExp(rs.getFloat("tempoFormacao"));
				candidato.setGenero(rs.getString("genero"));
			}
			
			
		}
		
		
		return null;
		
		
	}




}
