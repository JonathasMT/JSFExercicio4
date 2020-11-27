package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.EmpresaDaoJoanatas;
import br.edu.faculdadedelta.modelo.EmpresaJoanatas;

@ManagedBean
@SessionScoped
public class EmpresaControllerJoanatas {
	
	private EmpresaJoanatas modelo = new EmpresaJoanatas();
	private EmpresaDaoJoanatas dao = new EmpresaDaoJoanatas();
	
	private static final String PAGINA_CADASTRO = "cadastroEmpresa.xhtml";
	private static final String PAGINA_LISTAR = "listarEmpresa.xhtml";
	
	public EmpresaJoanatas getEmpresaJoanatas() {
		return modelo;
	}
	public void setEmpresaJoanatas(EmpresaJoanatas modelo) {
		this.modelo = modelo;
	}
	
	public void limparCampos() {
		modelo = new EmpresaJoanatas();
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
	
	public List<EmpresaJoanatas> getLista() {
		List<EmpresaJoanatas> listaRetorno = new ArrayList<EmpresaJoanatas>();
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
