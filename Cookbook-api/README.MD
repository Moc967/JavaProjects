# Projeto Cookbook usando Spring Boot Maven

<p>Este projeto foi desenvolvido utilizando o padrão de projeto MVC, utilizando o framework Spring Boot, e utilizando o framework Maven, agregando a integração com o banco MySQL</p>

<p>Para carregar a primeira versão do projeto, basta acessar o link https://start.spring.io/
 e na sequência marcar as opções conforme figura abaixo, adicionando as dependências 1 - Spring Data JPA, 2 - Spring Web,  3 - Lombok e 4 - MySQL driver</p> 

<p>Ao final, clicar em <b>generate</b> para que seja gerado o pacote no formato zip</p>

<p>Descompactar o arquivo .zip gerado e mover para o diretório onde deseja criar seu projeto.</p>

<img src="img/spring.png">

<p> Após abrir o projeto no InteliJ, aguardar a instalação/download dos pacotes spring boot, clicar em resources e abrir o <b>application.properties</b> </p>

<img src="img/resource.png">

<p>para adicionar as linhas que segue: </p>

spring.datasource.url=jdbc:mysql://localhost:3306/cookbook
spring.datasource.username=${cookbook.bd.user:root}
spring.datasource.password=${cookbook.bd.pwd:root}

<p>Alterar o arquivo de configuração das variáveis de ambiente para incluir o usuário e senha de acesso ao banco.</p>

<p>Executar o CookbookApiApplication e confirmar que o spring boot esta ativo </p>