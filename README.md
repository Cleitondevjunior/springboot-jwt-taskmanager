# Task Manager API

API REST para gerenciamento de tarefas desenvolvida com Spring Boot, Spring Security, JWT e PostgreSQL.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- PostgreSQL
- Maven
- Bean Validation

## Funcionalidades

- Cadastro de usuário
- Login com autenticação JWT
- Rotas protegidas
- CRUD completo de tarefas
- Listagem de tarefas do usuário autenticado
- Busca de tarefa por ID
- Filtro de tarefas por status
- Endpoint para dados do usuário logado
- Tratamento global de exceções
- Validação de dados de entrada
- Logs simples de ações

## Endpoints principais

### Autenticação
- `POST /auth/register`
- `POST /auth/login`

### Usuário
- `GET /users/me`

### Tarefas
- `POST /tasks`
- `GET /tasks`
- `GET /tasks/{id}`
- `PUT /tasks/{id}`
- `DELETE /tasks/{id}`
- `GET /tasks/status/{status}`

## Exemplo de autenticação

Após realizar login, envie o token no header:

```http
Authorization: Bearer SEU_TOKEN