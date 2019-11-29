![License MIT](https://img.shields.io/badge/license-MIT-blue.svg) [![](https://img.shields.io/docker/pulls/tamakirtt/alpine_confd_jdk18.svg)](https://hub.docker.com/r/tamakirtt/alpine_confd_jdk18 'Tamaki-app') [![](https://img.shields.io/docker/pulls/tamakirtt/mongo.svg)](https://hub.docker.com/r/tamakirtt/mongo 'Mongo')

Esse projeto utiliza as seguintes tecnologias:

Maven
AWS CodePipeline
Docker
Java/SpringBoot
ConfD
MongoDB

### Como usar localmente

Executar o git clone https://github.com/RenatoT/tamaki-app.git

Deve-se realizar o cadastro no site:
  https://developer.twitter.com/
	
Criar um app e gerar as seguintes chaves:
	
    twitter.consumer.key
    twitter.consumer.key.secret
    twitter.access.token
    twitter.access.token.secret

Esses valores devem ser incluidos nos arquivos 

    src\main\resources\application.properties
    src\main\resources\application-test.properties

## Executar o seguinte comando:
	  
    $ mvn package

Criação do Container
	$ docker build -t docker.io/tamakirtt/alpine_confd_jdk18 .
	$ docker push docker.io/tamakirtt/alpine_confd_jdk18:latest
		
### Base Imagem Docker
	
A imagem base para o mongodb está disponível na pasta mongoDB do projeto.
	
### Iniciar o container Twitter

	$ docker run -p 8090:8090 --name twitter tamakirtt/alpine_confd_jdk18:latest

### Iniciar o container MongoDB

	$ docker run -p 27017:27017 --name mongodb tamakirtt/mongo:latest

### AWS
Os buildspec estão configurados e diponibilizados na branch caso queira criar a pipeline do projeto na AWS CodePipeline.
