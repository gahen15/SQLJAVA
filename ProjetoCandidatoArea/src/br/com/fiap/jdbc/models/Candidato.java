package br.com.fiap.jdbc.models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Candidato {

		private long idCandidato;
		private String nome,formacao, endereco, telefone, email;
		private LocalDate dataNasc;
		private float tempoExp;
		private long idArea;
		private Genero genero;
		
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Candidato(String nome, String formacao, String endereco, String telefone, String dataNasc, float tempoExp,
				Genero genero, String email) {
			this.nome = nome;
			this.formacao = formacao;
			this.endereco = endereco;
			this.telefone = telefone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.dataNasc = LocalDate.parse(dataNasc, format);
			this.tempoExp = tempoExp;
			this.genero = genero;
			this.email = email;
		}
		
		public Candidato() {
		
		}

		public long getIdCandidato() {
			return idCandidato;
		}


		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getFormacao() {
			return formacao;
		}

		public void setFormacao(String formacao) {
			this.formacao = formacao;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public java.sql.Date getDataNasc() {
			return java.sql.Date.valueOf(dataNasc);
		}

		public void setDataNasc(java.sql.Date dataNasc) {
	
		    this.dataNasc = dataNasc.toLocalDate();
		}


		public float getTempoExp() {
			return tempoExp;
		}

		public void setTempoExp(float tempoExp) {
			this.tempoExp = tempoExp;
		}

		public long getIdArea() {
			return idArea;
		}

		public void setIdArea(long idArea) {
			this.idArea = idArea;
		}

		public String getGenero() {
			return genero.name();
		}

		
		

		    public void setGenero(String genero) {
		        if (genero != null) {
		            try {
		               
		                this.genero = Genero.valueOf(genero.trim().toUpperCase());
		            } catch (IllegalArgumentException e) {
		              
		                this.genero = Genero.NAO_INFORMADO;
		            }
		        } else {
		  
		            this.genero = Genero.NAO_INFORMADO;
		        }
		    }

		  


	
	
	
}
