package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.AlunoDaoJoanatas;
import br.edu.faculdadedelta.modelo.AlunoJoanatas;

@ManagedBean
@SessionScoped
public class AlunoControllerJoanatas {
	
	private AlunoJoanatas modelo = new AlunoJoanatas();
	private AlunoDaoJoanatas dao = new AlunoDaoJoanatas();
	
	private static final String PAGINA_CADASTRO = "cadastroAluno.xhtml";
	private static final String PAGINA_LISTAR = "listarAluno.xhtml";
	
	public AlunoJoanatas getAlunoJoanatas() {
		return modelo;
	}
	public void setAlunoJoanatas(AlunoJoanatas modelo) {
		this.modelo = modelo;
	}
	
	public void limparCampos() {
		modelo = new AlunoJoanatas();
	}
	
	public String salvar() throws ParseException {			
		try {
			if(modelo.getId() == null) {
				dao.incluir(modelo);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(modelo);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	
	public String editar() {
		return PAGINA_CADASTRO;
	}
	
	public String excluir() {
		try {
			dao.excluir(modelo);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_LISTAR;
	}
	
	public List<AlunoJoanatas> getLista() {
		List<AlunoJoanatas> listaRetorno = new ArrayList<AlunoJoanatas>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	public void exibirMensagem(String msg) {
		FacesMessage mensagem = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

}
