package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.BibliotecaDaoJoanatas;
import br.edu.faculdadedelta.modelo.BibliotecaJoanatas;

@ManagedBean
@SessionScoped
public class BibliotecaControllerJoanatas {
	
	private BibliotecaJoanatas modelo = new BibliotecaJoanatas();
	private BibliotecaDaoJoanatas dao = new BibliotecaDaoJoanatas();
	
	private static final String PAGINA_CADASTRO = "cadastroBiblioteca.xhtml";
	private static final String PAGINA_LISTAR = "listarBiblioteca.xhtml";
	
	public BibliotecaJoanatas getBibliotecaJoanatas() {
		return modelo;
	}
	public void setBibliotecaJoanatas(BibliotecaJoanatas modelo) {
		this.modelo = modelo;
	}
	
	public void limparCampos() {
		modelo = new BibliotecaJoanatas();
	}
	
	public String salvar() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date limite = sdf.parse("01/01/2022");				
		try {
			if(modelo.getDataCadastro().after(new Date())) {
				if(modelo.getDataCadastro().before(limite)) {
					if(modelo.getId() == null) {
						dao.incluir(modelo);
						exibirMensagem("Inclus�o realizada com sucesso!");
						limparCampos();
					} else {
						dao.alterar(modelo);
						exibirMensagem("Altera��o realizada com sucesso!");
						limparCampos();
					}
				}else {
					exibirMensagem("A Data de Cadastro deve ser menor que 01/01/2022");
				}
			}else {
				exibirMensagem("A Data de Cadastro deve ser maior que a data de hoje.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a opera��o. Tente novamente mais tarde. " + e.getMessage());
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
			exibirMensagem("Exclus�o realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a opera��o. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_LISTAR;
	}
	
	public List<BibliotecaJoanatas> getLista() {
		List<BibliotecaJoanatas> listaRetorno = new ArrayList<BibliotecaJoanatas>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a opera��o. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	public void exibirMensagem(String msg) {
		FacesMessage mensagem = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

}
