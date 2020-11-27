package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.EmpresaJoanatas;
import br.edu.faculdadedelta.util.Conexao;

public class EmpresaDaoJoanatas {
	
	public void incluir(EmpresaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO empresas(" + 
				"	nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa)" + 
				"	VALUES (?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeFantasia().trim());
		ps.setString(2, modelo.getCnpj().trim());
		ps.setString(3, modelo.getEndereco().trim());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void alterar(EmpresaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE empresas" + 
				"	SET nome_fantasia_empresa=?, cnpj_empresa=?, endereco_empresa=?, data_cadastro_empresa=?" + 
				"	WHERE id_empresa=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeFantasia().trim());
		ps.setString(2, modelo.getCnpj().trim());
		ps.setString(3, modelo.getEndereco().trim());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    ps.setLong(5, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void excluir(EmpresaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "DELETE FROM empresas WHERE id_empresa=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setLong(1, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public List<EmpresaJoanatas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		
		String sql = "SELECT id_empresa, nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa" + 
				"	FROM empresas;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<EmpresaJoanatas> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			EmpresaJoanatas modelo = new EmpresaJoanatas();
			modelo.setId(rs.getLong("id_empresa"));
			modelo.setNomeFantasia(rs.getString("nome_fantasia_empresa").trim());
			modelo.setCnpj(rs.getString("cnpj_empresa").trim());
			modelo.setEndereco(rs.getString("endereco_empresa").trim());
			modelo.setDataCadastro(rs.getDate("data_cadastro_empresa"));

			listaRetorno.add(modelo);
		}
		Conexao.fecharConexao(rs, ps ,conn);
		
		return listaRetorno;
	}

}
