package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VeiculoJoanatas;
import br.edu.faculdadedelta.util.Conexao;

public class VeiculoDaoJoanatas {
	
	public void incluir(VeiculoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO carros(" + 
				"	desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, desc_placa_carro, data_cadastro_carro)" + 
				"	VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeCarro().trim());
		ps.setString(2, modelo.getMarcaCarro().trim());
		ps.setInt(3, modelo.getAnoFabric());
		ps.setString(4, modelo.getPlaca().trim());
	    ps.setDate(5, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void alterar(VeiculoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE carros" + 
				"	SET desc_nome_carro=?, desc_marca_carro=?, ano_fabricacao_carro=?, desc_placa_carro=?, data_cadastro_carro=?" + 
				"	WHERE id_carro=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeCarro().trim());
		ps.setString(2, modelo.getMarcaCarro().trim());
		ps.setInt(3, modelo.getAnoFabric());
		ps.setString(4, modelo.getPlaca().trim());
	    ps.setDate(5, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    ps.setLong(6, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void excluir(VeiculoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "DELETE FROM carros WHERE id_carro=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setLong(1, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public List<VeiculoJoanatas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		
		String sql = "SELECT id_carro, desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, desc_placa_carro, data_cadastro_carro" + 
				"	FROM carros;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<VeiculoJoanatas> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			VeiculoJoanatas modelo = new VeiculoJoanatas();
			modelo.setId(rs.getLong("id_carro"));
			modelo.setNomeCarro(rs.getString("desc_nome_carro").trim());
			modelo.setMarcaCarro(rs.getString("desc_marca_carro").trim());
			modelo.setAnoFabric(rs.getInt("ano_fabricacao_carro"));
			modelo.setPlaca(rs.getString("desc_placa_carro"));
			modelo.setDataCadastro(rs.getDate("data_cadastro_carro"));
			
			listaRetorno.add(modelo);
		}
		Conexao.fecharConexao(rs, ps ,conn);
		
		return listaRetorno;
	}

}
