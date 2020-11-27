package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.VeiculoDaoJoanatas;
import br.edu.faculdadedelta.modelo.VeiculoJoanatas;

@ManagedBean
@SessionScoped
public class VeiculoControllerJoanatas {
	
	private VeiculoJoanatas modelo = new VeiculoJoanatas();
	private VeiculoDaoJoanatas dao = new VeiculoDaoJoanatas();
	
	private static final String PAGINA_CADASTRO = "cadastroVeiculo.xhtml";
	private static final String PAGINA_LISTAR = "listarVeiculo.xhtml";
	
	public VeiculoJoanatas getVeiculoJoanatas() {
		return modelo;
	}
	public void setVeiculoJoanatas(VeiculoJoanatas modelo) {
		this.modelo = modelo;
	}
	
	public void limparCampos() {
		modelo = new VeiculoJoanatas();
	}
	
	public String salvar() throws ParseException {	
		try {
			if(modelo.getDataCadastro().after(new Date())) {
				if(modelo.getId() == null) {
					dao.incluir(modelo);
					exibirMensagem("Inclusão realizada com sucesso!");
					limparCampos();
				} else {
					dao.alterar(modelo);
					exibirMensagem("Alteração realizada com sucesso!");
					limparCampos();
				}
			}else {
				exibirMensagem("A Data de Cadastro deve ser maior que a data de hoje.");
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
	
	public List<VeiculoJoanatas> getLista() {
		List<VeiculoJoanatas> listaRetorno = new ArrayList<VeiculoJoanatas>();
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
