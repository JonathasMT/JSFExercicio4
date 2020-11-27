package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class AlunoJoanatas {
	private Long id;
	private String nomeAluno;
	private int idadeAluno;
	private String grauIntrucao;
	private Date dataCadastro;
	
	public AlunoJoanatas() {
	}

	public AlunoJoanatas(Long id, String nomeAluno, int idadeAluno, String grauIntrucao, Date dataCadastro) {
		this.id = id;
		this.nomeAluno = nomeAluno;
		this.idadeAluno = idadeAluno;
		this.grauIntrucao = grauIntrucao;
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public int getIdadeAluno() {
		return idadeAluno;
	}

	public void setIdadeAluno(int idadeAluno) {
		this.idadeAluno = idadeAluno;
	}

	public String getGrauIntrucao() {
		return grauIntrucao;
	}

	public void setGrauIntrucao(String grauIntrucao) {
		this.grauIntrucao = grauIntrucao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoJoanatas other = (AlunoJoanatas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
