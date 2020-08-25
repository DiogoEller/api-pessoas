package br.com.apiPessoas.bean;

import javax.faces.bean.ManagedBean;

import br.com.apiPessoas.dao.DAO;
import br.com.apiPessoas.modelo.Pessoa;

@ManagedBean
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void gravar() {
		System.out.println("Gravando pessoa " + this.pessoa.getNome());

//		if (pessoa.getNascimento() == null) {
//			throw new RuntimeException("Pessoa deve ter pelo menos uma Pessoa.");
//		}

		new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
	}

}
