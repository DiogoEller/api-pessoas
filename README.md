# api-pessoas
Tecnologias usadas:
- JPA com hibernate, usando JSF no front;
- apache-tomcat-9.0.37.

Como Usar:
- Precisa rodar a classe PopulaBanco.java para popular o banco com 1 usuario e 1 pessoa;
- Usar login.xhtml para acessar o sistema (Email: dbrunoeller@hotmail.com  Senha: 123)
- O primeiro form são os dados da pessoa, podesse cadastrar ela normal e clicar em "Salvar Pessoa"
- Ela será inserida no outro form logo abaixo, com as opções de "alterar" ou "remover"
- Caso clique em "alterar", os dados da pessoa irão aparecer no form de cima, e logo após finalizada a alteração, 
devesse salvar a pessoa (nota: não consegui resolver um problema com o ID da pessoa, então quando for "alterada" 
ela na verdade será adicionada novamente... mas a lógica pode ser vista no método gravar() da classe PessoaBean.java)
