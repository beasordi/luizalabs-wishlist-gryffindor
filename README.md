# Wish list - Gryffindor Squad
###Introdução

Este projeto foi proposto na segunda edição do Luiza Code, programa de formação em tecnologia, exclusivo para mulheres, criado pelo Magazine Luiza.
A ideia é montar o backend de uma Wishlist (lista de desejos), para que, em um cenário de e-commerce, o cliente possa realizar a busca e seleção de produtos de sua preferência e armazená-los na sua Wishlist, sendo que a qualquer momento o cliente possa visualizar sua Wishlist completa.

####Processo de instalação

- Instale o IntelliJ IDEA - Community (versão gratuita)
- Instale a JDK, JRE e a JVM, para conseguir rodar códigos java na sua máquina
- Instale o MySQL - Workbench 8.0

#### Dependencias de software

- IntelliJ IDEA
- Java 1.8
- Hibernate 6.0.13
- JAVAX 2.0.1
- Lombok
- Maven 2.4.5
- Spring boot 2.4.5
	JPA
	Web
	Test
- MySQL - Workbench 8.0
	Connector Java 8.0.23
		
Outras IDEs podem compilar o código mas não são suportadas oficialmente.
	
#### Build e test

Para rodar o código é necessário seguir as etapas abaixo:

- Clone o repositório
- Suba o banco na sua máquina, conforme os passos abaixo
- Builde o código

Para realizar a subida do banco siga as etapas abaixo:

- Windows

	- Acesse o menu iniciar
	- Digite "Serviços"
	- Abra o aplicativo
	- Localize o MySQL
	- Clique com o botão direito
	- Selecione "Propriedades"
	- Em "Tipo de inicialização", selecione "Automático" ou clique em "iniciar", no menu lateral
	- Aplique a alteração
	- Clique em OK
	
- MAC

	- Acesse o menu iniciar
	- Digite "Terminal"
	- Abra o aplicativo
	- Digite: mysql.server  start
	
- Linux (Ubunto)

	- Abra o menu iniciar
	- Digite "Terminal"
	- Abra o aplicativo
	- Digite: /etc/init.d/mysql start

Acesse o link para mais detalhes no Linux: https://cafetiria.wordpress.com/2015/03/11/como-reiniciar-o-mysql-no-linux/
		
####Retorno esperado

- Criação de banco de dados automática no Workbench

- Visualização de requests via Swagger Produção no link a seguir: https://luizalabs-wishlist-gryffindor.herokuapp.com/swagger-ui.html#/
- Visualização de requests via Swagger Local no link a seguir: http://localhost:8080/swagger-ui.html	
    Caso tenha dificuldades de acessar o Swagger, verifique se a porta que sua máquina esta utilizando é a 8080, caso seja diferente, altere o numero na URL.

####Metodologias ágeis

Para o desenvolvimento do projeto foi utilizada uma adaptação do Scrum e KanBan, verifique no link a seguir: https://trello.com/b/8lAv6tVq/luiza-labs-scrum
