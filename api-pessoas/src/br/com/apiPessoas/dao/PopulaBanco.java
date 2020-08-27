package br.com.apiPessoas.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.apiPessoas.modelo.Pessoa;
import br.com.apiPessoas.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Pessoa diogo = geraPessoa("Diogo Eller", "Masculino", "dbrunoeller@hotmail.com", "01/01/1990",
				 "Blumenau", "Brasileiro", "950.611.220-70");

		em.persist(diogo);
		
		Usuario usuario = geraUsuario("dbrunoeller@hotmail.com","123");

		em.persist(usuario);
		
		em.getTransaction().commit();
		em.close();

	}

	private static Pessoa geraPessoa(String nome, String sexo, String email, String nasc,
			String nat, String nac, String cpf) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		pessoa.setEmail(email);
		pessoa.setNascimento(parseData(nasc));
		pessoa.setNaturalidade(nat);
		pessoa.setNacionalidade(nac);
		pessoa.setCpf(cpf);
		return pessoa;
	}
	
	private static Usuario geraUsuario(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		return usuario;
	}


	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
