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

import br.edu.faculdadedelta.dao.VendaDaoJoanatas;
import br.edu.faculdadedelta.modelo.VendaJoanatas;

@ManagedBean
@SessionScoped
public class VendaControllerJoanatas {
	
	private VendaJoanatas modelo = new VendaJoanatas();
	private VendaDaoJoanatas dao = new VendaDaoJoanatas();
	
	private static final String PAGINA_CADASTRO = "cadastroVenda.xhtml";
	private static final String PAGINA_LISTAR = "listarVenda.xhtml";
	
	public VendaJoanatas getVendaJoanatas() {
		return modelo;
	}
	public void setVendaJoanatas(VendaJoanatas modelo) {
		this.modelo = modelo;
	}
	
	public void limparCampos() {
		modelo = new VendaJoanatas();
	}
	
	public String salvar() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date limite = sdf.parse("01/01/2022");				
		try {
			if(modelo.getDataVenda().after(new Date())) {
				if(modelo.getDataVenda().before(limite)) {
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
					exibirMensagem("A Data da Venda deve ser menor que 01/01/2022");
				}
			}else {
				exibirMensagem("A Data da Venda deve ser maior que a data de hoje.");
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
	
	public List<VendaJoanatas> getLista() {
		List<VendaJoanatas> listaRetorno = new ArrayList<VendaJoanatas>();
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
