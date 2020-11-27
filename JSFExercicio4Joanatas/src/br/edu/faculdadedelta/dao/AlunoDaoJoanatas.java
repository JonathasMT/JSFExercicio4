package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoJoanatas;
import br.edu.faculdadedelta.util.Conexao;

public class AlunoDaoJoanatas {
	
	public void incluir(AlunoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO alunos(" + 
				"	nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno)" + 
				"	VALUES (?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeAluno().trim());
		ps.setInt(2, modelo.getIdadeAluno());
		ps.setString(3, modelo.getGrauIntrucao().trim());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void alterar(AlunoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE alunos" + 
				"	SET nome_aluno=?, idade_aluno=?, grau_instrucao_aluno=?, data_cadastro_aluno=?" + 
				"	WHERE id_aluno=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getNomeAluno().trim());
		ps.setInt(2, modelo.getIdadeAluno());
		ps.setString(3, modelo.getGrauIntrucao().trim());
	    ps.setDate(4, new java.sql.Date(modelo.getDataCadastro().getTime()));
	    ps.setLong(5, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void excluir(AlunoJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "DELETE FROM alunos WHERE id_aluno=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setLong(1, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public List<AlunoJoanatas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		
		String sql = "SELECT id_aluno, nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno" + 
				"	FROM alunos;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<AlunoJoanatas> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			AlunoJoanatas modelo = new AlunoJoanatas();
			modelo.setId(rs.getLong("id_aluno"));
			modelo.setNomeAluno(rs.getString("nome_aluno").trim());
			modelo.setIdadeAluno(rs.getInt("idade_aluno"));
			modelo.setGrauIntrucao(rs.getString("grau_instrucao_aluno").trim());
			modelo.setDataCadastro(rs.getDate("data_cadastro_aluno"));
			
			listaRetorno.add(modelo);
		}
		Conexao.fecharConexao(rs, ps ,conn);
		
		return listaRetorno;
	}

}
