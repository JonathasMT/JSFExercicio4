package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VendaJoanatas;
import br.edu.faculdadedelta.util.Conexao;

public class VendaDaoJoanatas {
	
	public void incluir(VendaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas(" + 
				"	desc_cliente, desc_produto, valor_produto, data_venda)" + 
				"	VALUES (?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getCliente().trim());
		ps.setString(2, modelo.getProduto().trim());
		ps.setDouble(3, modelo.getValor());
	    ps.setDate(4, new java.sql.Date(modelo.getDataVenda().getTime()));
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void alterar(VendaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE vendas" + 
				"	SET desc_cliente=?, desc_produto=?, valor_produto=?, data_venda=?" + 
				"	WHERE id_venda=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, modelo.getCliente().trim());
		ps.setString(2, modelo.getProduto().trim());
		ps.setDouble(3, modelo.getValor());
	    ps.setDate(4, new java.sql.Date(modelo.getDataVenda().getTime()));
	    ps.setLong(5, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public void excluir(VendaJoanatas modelo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setLong(1, modelo.getId());
	    
	    ps.executeUpdate();
	    Conexao.fecharConexao(null, ps ,conn);
	}
	
	public List<VendaJoanatas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		
		String sql = "SELECT id_venda, desc_cliente, desc_produto, valor_produto, data_venda" + 
				"	FROM vendas;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<VendaJoanatas> listaRetorno = new ArrayList<>();
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			VendaJoanatas modelo = new VendaJoanatas();
			modelo.setId(rs.getLong("id_venda"));
			modelo.setCliente(rs.getString("desc_cliente").trim());
			modelo.setProduto(rs.getString("desc_produto").trim());
			modelo.setValor(rs.getDouble("valor_produto"));
			modelo.setDataVenda(rs.getDate("data_venda"));
			
			listaRetorno.add(modelo);
		}
		Conexao.fecharConexao(rs, ps ,conn);
		
		return listaRetorno;
	}

}
