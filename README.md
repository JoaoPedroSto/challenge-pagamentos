# Challenge Pagamentos
Desafio proposto referente a um sistemas de pagamento.

# Tecnologias
**Maven** - Gerenciamento de dependências do projeto.

**Lombok** - Responsável por gerar os getter, setter e o builder das classes de modelo.

**JUnit** - Para testes unitários.

**ModelMapper** - Para realizar o mapeamento das classes dto para entity.

**Kafka** - Mensageria escolhida para notificação.

**MongoDB** - Banco NoSQL para armazenamento de dados das entidades.

No projeto, são utilizado conceitos de S.O.L.I.D.

# Iniciando o projeto

Após do download do fonte e utilizando de um terminal, navegar até o diretório onde se encontra o projeto e executar o
comando do maven para gerar o .jar da aplicação:
```shell
mvn clean install
```
Na raiz do projeto se encontra o arquivo docker-compose, onde estão registrados os serviços necessários para que a 
aplicação funcione.

Com o docker já instalado na máquina, executar o comando a seguir:

```shell
docker-compose up -d
```

Ao executar, o seguintes serviços devem estar executando:
* application
* kafdrop
* kafka
* mongo-express
* mongodb
* zookeeper

Neste ponto a aplicação já está pronta para ser executada. As API's são documentadas pelo swagger e podem ser vistas na
rota: http://localhost:8085/swagger-ui/index.html

# Processo de desenvolvimento
Durante o começo do desenvolvimento, foquei em realizar o CRUD de forma funcional, para depois aplicar as regras associadas
ao desafio. Optei por utilizar do MongoDB por questões de performance e para simplificar o a estrutura de dados utilizada, 
focando em somente um documento pagamentos.

Pensando em uma integração com outros sistemas, utilizei uma mensageria com um único tópico para receber as mensagens de 
alteração, no caso utilizando a tecnologia Kafka.

# Mongo Express
Interface para acessar o banco de dados MongoDB.
Após a execução do docker-compose, acessar o link a seguir para visualizar os documentos:
http://localhost:8081/

# Kafdrop
Interface para acessar o ambiente kafka da aplicação e suas respectivas mensagens.
Após a execução do docker-compose, acessar o link a seguir para visualizar as mensagens:
http://localhost:19000/
