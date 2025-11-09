# Desafio Técnico: API de Cadastro de Usuários (Full-Stack) - Por Miguel Varella

Este projeto é uma aplicação web full-stack para o gerenciamento de usuários, permitindo operações de **CRUD** (Criar, Ler, Atualizar e Excluir).

A arquitetura é totalmente containerizada usando Docker e orquestrada com Docker Compose, separando o backend (API REST em Spring Boot) do frontend (Aplicação Vue.js).

## Funcionalidades

* **Listar Usuários:** Visualiza todos os usuários cadastrados.
* **Cadastrar Usuário:** Cria um novo usuário.
* **Editar Usuário:** Atualiza as informações de um usuário existente.
* **Excluir Usuário:** Remove um usuário do banco de dados.

## Tecnologias Utilizadas

| Categoria | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Backend** | **Java (Spring Boot)** | API REST para gerenciar os dados dos usuários. |
| | **Spring Data JPA** | Persistência de dados. |
| | **H2 Database** | Banco de dados em memória (ideal para desafios). |
| **Frontend** | **Vue.js (Vite)** | Interface reativa para interação do usuário. |
| | **Axios** | Cliente HTTP para consumir a API do backend. |
| | **Nginx** | Servidor web leve para servir os arquivos estáticos do Vue. |
| **Infra** | **Docker & Docker Compose**| Containerização e orquestração dos serviços. |

---

## Como Executar o Projeto

Para rodar este projeto, você precisa ter **Docker** e **Docker Compose** instalados.

### 1. Clone o Repositório

```bash
git clone https://github.com/mglvarella/fullstack-user-crud.git
```
### 2. Dentro do diretório do projeto:
```bash
docker compose up --build
```
### Frontend (Aplicação Web):
```bash
http://localhost:3000
```
### Backend (API):
```bash
http://localhost:8080
```
