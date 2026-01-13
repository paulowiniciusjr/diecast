# 🚗 Diecast – Catálogo de Veículos em Miniatura - BACKEND

Projeto pessoal para catalogação de veículos diecast, desenvolvido com Java + Spring Boot no backend e Angular 20 no frontend, seguindo padrões modernos de arquitetura, segurança e organização de código.

## 📌 Visão Geral

O sistema permite:

Autenticação de usuários via JWT

Acesso seguro a endpoints protegidos

Listagem e gerenciamento de veículos em miniatura

A arquitetura foi pensada para ser escalável, segura e de fácil manutenção, utilizando boas práticas atuais tanto no backend quanto no frontend.

## 🧩 Backend

🔧 Stack

Java 17+

Spring Boot

Spring Security

JWT (JSON Web Token)

JPA / Hibernate

REST API

## 🏗️ Arquitetura

O backend segue uma arquitetura em camadas, com separação clara de responsabilidades:

```bash
Controller → Service → Repository → Database
```

Controller: expõe endpoints REST

Service: regras de negócio

Repository: acesso aos dados

Config: segurança, JWT e CORS

## 🔐 Segurança e Autenticação

- Autenticação stateless usando JWT

- Endpoint de login: /auth/login

Retorna:

- token

- expiresIn

Token enviado via header HTTP:

```bash
Authorization: Bearer <token>
```

## 🧱 Filtro JWT

- Implementado com OncePerRequestFilter

- Valida o token a cada requisição

- Injeta o usuário autenticado no SecurityContext

Benefícios:

- Segurança centralizada

- Backend não depende do frontend para validação

## 🛡️ Autorização

Controle de acesso com @PreAuthorize

Baseado em roles (USER, ADMIN)

Exemplo:

```bash
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
```

## 🌐 CORS

Configuração global via CorsConfigurationSource

Permite:
```bash
Origem específica (http://localhost:4200)
```

```bash
Headers customizados (Authorization)
```

```bash
Métodos REST (GET, POST, PUT, DELETE, OPTIONS)
```


## 🔄 Fluxo de Autenticação (End-to-End)

```python
Usuário faz login
        ↓
Backend gera JWT
        ↓
Token salvo no localStorage
        ↓
Interceptor adiciona Bearer Token
        ↓
AuthGuard libera rota
        ↓
Backend valida JWT
```

## 📐 Padrões e Boas Práticas Utilizadas

- Arquitetura em camadas (backend)

- Autenticação stateless

- JWT

- Separation of Concerns

- Feature-based architecture (frontend)

- Standalone Components

- Guards e Interceptors funcionais

- Lazy loading por rotas

## 🏁 Conclusão

O projeto Diecast foi desenvolvido seguindo padrões modernos de arquitetura e desenvolvimento, com foco em segurança, organização de código e escalabilidade. A base atual permite evolução fácil para novas funcionalidades como CRUD completo, controle de permissões mais avançado e melhorias de UX.



