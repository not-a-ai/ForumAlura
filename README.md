# 📚 Fórum Challenge - API REST com Spring Boot

Projeto de um fórum de discussão desenvolvido com Spring Boot. A API permite a criação e gerenciamento de usuários, cursos, tópicos e respostas, com autenticação via JWT.

---

## ✅ Funcionalidades

- 🔐 Autenticação JWT (login)
- 👤 CRUD de Usuários
- 🎓 CRUD de Cursos
- 📝 CRUD de Tópicos
- 💬 Criação de Respostas para tópicos
- 🔍 Filtros e listagens
- 📌 Validação e tratamento de erros

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- Flyway (migrations)
- PostgreSQL
- Lombok
- JWT (JSON Web Token)

---


---

## 🔐 Autenticação

- Login via `POST /login` com `email` e `senha`
- Retorna um token JWT
- Para acessar rotas protegidas, envie no cabeçalho:

Authorization: Bearer SEU_TOKEN_JWT


---

## 🔁 Endpoints principais

### 👤 Usuários

- `POST /usuarios` — Criar novo usuário  
- `GET /usuarios` — Listar todos  
- `GET /usuarios/{id}` — Detalhar usuário  
- `PUT /usuarios/{id}` — Atualizar  
- `DELETE /usuarios/{id}` — Deletar  

### 🎓 Cursos

- `POST /cursos` — Criar curso  
- `GET /cursos` — Listar todos  
- `GET /cursos/{id}` — Detalhar curso  
- `PUT /cursos/{id}` — Atualizar  
- `DELETE /cursos/{id}` — Deletar  

### 📝 Tópicos

- `POST /topicos` — Criar tópico (autenticado)  
- `GET /topicos` — Listar todos  
- `GET /topicos/{id}` — Detalhar por ID  
- `PUT /topicos/{id}` — Atualizar título, mensagem e status  
- `DELETE /topicos/{id}` — Deletar tópico  

### 💬 Respostas

- `POST /respostas` — Criar resposta para tópico  
- Inclui data automática e campo `solucao` (padrão: false)

---

## 🛠 Configuração e execução

1. Clone o projeto:
```bash
git clone https://github.com/seu-usuario/forum-challenge.git
```
2. Configure o application.properties
3. Execute o projeto:

## 📌 Observações

- A autenticação é obrigatória para criação de tópicos e respostas.

- dataCriacao é automática nos tópicos e respostas.

- Um tópico pertence a um curso e um autor (usuário).

- As atualizações de tópico não permitem trocar o curso original.

- Flyway gerencia as migrations do banco de dados.

- Projeto ideal para estudos com Spring Security + JWT + JPA.





