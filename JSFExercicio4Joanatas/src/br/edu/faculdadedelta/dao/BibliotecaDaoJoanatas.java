package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.BibliotecaJoanatas;
import br.edu.faculdadedelta.util.Conexao;

public class BibliotecaDaoJoanatas {
	
	public void incluir(BibliotecaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO livros(" + 
				"	nome_livro, desc_editora, valor_livro, data_cadastro_livro)" + 
				"	VALUES (?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeLivro().trim());
		ps.setString(2, modelo.getEditora().trim());
		ps.setDouble(3, modelo.getValor());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void alterar(BibliotecaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE livros" + 
				"	SET nome_livro=?, desc_editora=?, valor_livro=?, data_cadastro_livro=?" + 
				"	WHERE id_livro=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeLivro().trim());
		ps.setString(2, modelo.getEditora().trim());
		ps.setDouble(3, modelo.getValor());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    ps.setLong(5, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void excluir(BibliotecaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "DELETE FROM livros WHERE id_livro=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setLong(1, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public List<BibliotecaJoanatas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		
		String sql = "SELECT id_livro, nome_livro, desc_editora, valor_livro, data_cadastro_livro" + 
				"	FROM livros;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<BibliotecaJoanatas> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			BibliotecaJoanatas modelo = new BibliotecaJoanatas();
			modelo.setId(rs.getLong("id_livro"));
			modelo.setNomeLivro(rs.getString("nome_livro").trim());
			modelo.setEditora(rs.getString("desc_editora").trim());
			modelo.setValor(rs.getDouble("valor_livro"));
			modelo.setDataCadastro(rs.getDate("data_cadastro_livro"));
			
			listaRetorno.add(modelo);
		}
		Conexao.fecharConexao(rs, ps ,conn);
		
		return listaRetorno;
	}

}
