package br.com.apiPessoas.bean;

import javax.faces.bean.ManagedBean;

import br.com.apiPessoas.dao.UsuarioDAO;
import br.com.apiPessoas.modelo.Usuario;

@ManagedBean
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuario " + this.getUsuario().getEmail());
		
		boolean existe = new UsuarioDAO().existe(this.usuario);
		if (existe) {
			return "pessoa?faces-redirect=true";			
		}
		
		return null;
	}

}
