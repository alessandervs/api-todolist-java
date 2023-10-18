# Todo List API

API REST para fins de estudo  que eraliza a criação de todo list com validação de usuário.
OBS: o banco de dados da API é em memória, sendo assim qualquer alteração na aplicação, redeploy apagarão os dados já cadastrados, sendo necessário cadastrar novamente o usuário.

Dúvidas e solicitações do token de acesso a API, devem ser enviadas para o e-mail alessander.victor.dev@gmail.com com o Assunto: TOKEN API TODOLIST.

Recursos disponíveis para acesso via API:
* [**Cadastrar usuário**](#reference/recursos/usuario)
* [**Criar uma tarefa**](#reference/recursos/tarefa)
* [**Listar as tarefas do usuário**](#reference/recursos/tarefa)
* [**Atualizar uma tarefa**](#reference/recursos/tarefa)



## URLs de acesso

URL homologação: https://api-todolist-java.onrender.com/api


## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna lista de tarefas do usuário. |
| `POST` | Utilizado para criar uma nova tarefa. |
| `PUT` | Atualiza dados de uma tarefa do usuário. |

# Recursos da API

# Cadastrar um usuário [/users]

### CreateUser[POST /users]
Cadastrar um usuário para utilziar a API de Todo List.

+ Attributes (object)
  + username (string, required) - nome de usuário.
  + password (string, required) - senha com no máximo 15 dígitos.
  + name (string, required) - Nome completo do usuário.

+ Request (application/json)

    + Headers

        Authorization: Bearer [access_token]

    + Body

        {
          "username":"john",
          "password": "123456as",
          "name": "John Doe"
        }

+ Response 201 (application/json)

    + Headers

          Content-Type	application/json

    + Body

            {
              "id": UUID,
              "username": nome de usuário,
              "name": "nome completo do usuário",
              "createdAt": "data e hora da criação do usuário"
            }

+ Response 400 (application/json)

          {
              "errCode": 400,
              "errMsg": "Sem permissão para cadastrar um usuário. Verifique seu \"access_token\".",
              "errObs": "access_denied",
              "errFields": null,
              "errUrl": "/api/users"
          }



### CreateTask [Post /tasks]

+ Request (application/json)

    + Headers

      + Authorization: Basic
          + username: "usuário cadastrado"
          + password: "senha cadastrada"
    
    + body 

        {
          "title":"Tarefa 01",
          "description": "criar api ",
          "priority": "ALTA",
          "startAt": "2023-10-16T10:00:01.0",
          "endAt":"2023-10-20T10:00:01.0"
        }
  

+ Response 200 (application/json)

         {
            "id": UUID,
            "userId": UUID,
            "description": "criar api ",
            "title": "Tarefa 01",
            "startAt": "2023-10-16T10:00:01",
            "endAt": "2023-10-20T10:00:01",
            "priority": "ALTA",
            "createdAt": "2023-10-15T19:07:19.701208092"
          }

+ Response 400 (application/json)

          {
              "errCode": 401,
              "errMsg": "Sem permissão para cadastrar um usuário. Verifique seu \"access_token\".",
              "errObs": "access_denied",
              "errFields": null,
              "errUrl": "/api/tasks"
          }

