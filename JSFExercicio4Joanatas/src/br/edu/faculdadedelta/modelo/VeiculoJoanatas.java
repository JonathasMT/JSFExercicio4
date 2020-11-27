package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class VeiculoJoanatas {
	private Long id;
	private String nomeCarro;
	private String marcaCarro;
	private int anoFabric;
	private String placa;
	private Date dataCadastro;
	
	public VeiculoJoanatas() {
	}

	public VeiculoJoanatas(Long id, String nomeCarro, String marcaCarro, int anoFabric, String placa,
			Date dataCadastro) {
		this.id = id;
		this.nomeCarro = nomeCarro;
		this.marcaCarro = marcaCarro;
		this.anoFabric = anoFabric;
		this.placa = placa;
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCarro() {
		return nomeCarro;
	}

	public void setNomeCarro(String nomeCarro) {
		this.nomeCarro = nomeCarro;
	}

	public String getMarcaCarro() {
		return marcaCarro;
	}

	public void setMarcaCarro(String marcaCarro) {
		this.marcaCarro = marcaCarro;
	}

	public int getAnoFabric() {
		return anoFabric;
	}

	public void setAnoFabric(int anoFabric) {
		this.anoFabric = anoFabric;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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
		VeiculoJoanatas other = (VeiculoJoanatas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
