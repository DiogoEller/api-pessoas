package br.com.apiPessoas.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.apiPessoas.dao.DAO;
import br.com.apiPessoas.dao.JPAUtil;
import br.com.apiPessoas.modelo.Pessoa;
import br.com.apiPessoas.modelo.Usuario;

@ManagedBean
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return new DAO<Pessoa>(Pessoa.class).listaTodos();
	}

	public void gravar() {

		if (validaCPF(this.pessoa.getCpf())) {
			if (this.pessoa.getId() == null) {
				System.out.println("Adicionando pessoa " + this.pessoa.getNome());
				new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
			} else {
				System.out.println("Atualizando pessoa " + this.pessoa.getNome());
				new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
			}
			this.pessoa = new Pessoa();
		} else {
			throw new ValidatorException(new FacesMessage("CPF já existe no sistema"));
		}
	}

	public boolean validaCPF(String cpf) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Pessoa> query = em.createQuery("select p "
				+ "from Pessoa p "
				+ "where cpf = :pCpf", Pessoa.class);
		
		query.setParameter("pCpf", cpf);
		
		try {
			Pessoa resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return true;
		}
		
		em.close();
		
		return false;
	}
	
	public void remover(Pessoa pessoa) {
		System.out.println("Removendo pessoa " + this.pessoa.getNome());
		new DAO<Pessoa>(Pessoa.class).remove(pessoa);
	}
	
	public void carregar(Pessoa pessoa) {
		System.out.println("Removendo pessoa " + this.pessoa.getNome());
		this.pessoa = pessoa;
	}
}
