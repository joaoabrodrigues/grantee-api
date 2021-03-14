# grantee-api

### Modelagem de dados
Conforme exemplificado na imagem abaixo, a modelagem foi pensada em três tabelas. São elas:
1) grantee: tabela responsável por armazenar os dados de um favorecido;
2) grantee_account: tabela responsável por armazenar os dados da conta de um favorecido;
3) user_grantee: tabela que relaciona os favorecidos com um usuário do sistema.
![image](img/er.png)


## App Getting Started

### Requirements
* Java >= 11.0.7
* Docker and Docker-Compose

### Running app
```shell
docker-compose up -d && sleep 10 && ./gradlew build bootRun -x test
```

### Running tests
```shell
./gradlew test
```

Disclaimer: 
grantee -> a person who receive money transfer