# TODO-LIST API 

TODO List é uma API que disponibiliza endpoints para gerenciamento de tarefas.

O projeto foi desenvolvido em Java utilizando Spring Boot e banco de dados MySQL.


## Dependências

- Docker;
- Docker compose;
- Java 11 ou superior;
- Maven 3.6.0 ou superior.

## Endpoints

### Cadastrar tarefa

Método HTTP: POST

Endpoint:  `<HOST>`:`<PORT>`/tasks

Parâmetros do corpo da requisição (json):

-   **description:**  Descrição da atividade, pode ser inserido um texto livre neste parâmetro.
-   **status:**  Status da atividade, apenas é possível passar os valores PENDING ou COMPLETED.

```
{
	"description": "<DESCRICAO>",
    "status": "<STATUS>"
}
```

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X POST -d '{"description":"Teste de Tarefa","status":"PENDING"}' http://localhost:8080/tasks
```

### Consultar todas as tarefas

Método HTTP: GET

Endpoint:  `<HOST>`:`<PORT>`/tasks

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/tasks
```

### Consultar tarefa por ID

Método HTTP: GET

Endpoint:  `<HOST>`:`<PORT>`/tasks/`<ID>`

Parâmetros da URL:

-   **id:**  ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/tasks/1
```

### Editar tarefa

Método HTTP: PUT

Endpoint:  `<HOST>`:`<PORT>`/tasks/

Parâmetros da URL:

-   **id:**  ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Parâmetros do corpo da requisição (json):

-   **description:**  Descrição da atividade, pode ser inserido um texto livre neste parâmetro
-   **status:**  Status da atividade, apenas é possível passar os valores PENDING ou COMPLETED

```
{
    "id": "<ID>",
    "description": "<DESCRICAO>",
    "status": "<STATUS>"
}
```

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X PUT -d '{"description":"Teste de Tarefa","status":"COMPLETED"}' http://localhost:8080/tasks/1
```

### Excluir tarefa

Método HTTP: DELETE

Endpoint:  `<HOST>`:`<PORT>`/tasks/`<ID>`

Parâmetros da URL:

-   **id:**  ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/tasks/1
```

## Helthcheck

É possível consultar o status do projeto através do endereço  `http://<HOST>:<PORT>/actuator/health`.

Exemplo:  [http://localhost:8080/actuator/health/](http://localhost:8000/actuator/health/)

## Métricas

É possível consultar o tempo e status das requisições no endereço  

`http://<HOST>:<PORT>`. 

Exemplo: http://localhost:3000  **GRAFANA**

`http://<HOST>:<PORT>/graph`. 

Exemplo: http://localhost:9090/graph **PROMETHEUS**

# DEPLOY API NO DOCKER

### DOCKER PULL COMMAND

```
 docker pull lukxsousa/tasksproject
```

Link Docker Hub: https://hub.docker.com/r/lukxsousa/tasksproject
