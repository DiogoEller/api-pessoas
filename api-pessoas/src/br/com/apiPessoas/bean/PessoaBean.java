package br.com.apiPessoas.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.apiPessoas.dao.DAO;
import br.com.apiPessoas.dao.JPAUtil;
import br.com.apiPessoas.modelo.Pessoa;

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
		System.out.println("Gravando pessoa " + this.pessoa.getNome());

		if (validaCPF(this.pessoa.getCpf())) {
			throw new ValidatorException(new FacesMessage("CPF já existe no sistema"));
		} else {
			new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
			this.pessoa = new Pessoa();
		}
	}

	public boolean validaCPF(String cpf) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query q = em.createQuery("select count(*) from Pessoa where cpf = :cpf ");
		q.setParameter("cpf", "\""+cpf+"\"");
		Integer teste = q.getFirstResult();
		em.close();
		
		if (teste != null && teste > 0) {
			return false;
		} else {
			return true;
		}
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
