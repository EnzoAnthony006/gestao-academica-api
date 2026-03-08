# 📚 API de Gestão de Cursos e Alunos

API REST desenvolvida com Spring Boot para gerenciamento de cursos, alunos e matrículas, com autenticação JWT e controle de acesso por perfil de usuário.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.4
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Maven
- Swagger / OpenAPI 3

## ⚙️ Como rodar o projeto

### Pré-requisitos
- Java 17+
- MySQL rodando localmente
- Maven

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/api-gestao.git
cd api-gestao
```

2. Crie o arquivo `src/main/resources/application-local.properties` baseado no exemplo:
```bash
cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
```

3. Preencha com seus dados:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aula_jpa?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

security.config.prefix=Bearer
security.config.key=sua_chave_secreta
security.config.expiration=3600000
```

4. Rode a aplicação:
```bash
./mvnw spring-boot:run
```

### Documentação da API

Acesse o Swagger após subir a aplicação:
```
http://localhost:8080/swagger-ui/index.html
```

## 🔐 Autenticação

A API usa JWT. Para acessar endpoints protegidos:

1. Cadastre um usuário em `POST /auth/cadastro`
2. Faça login em `POST /auth/login`
3. Use o token retornado no header: `Authorization: Bearer <token>`

## 👥 Perfis de acesso

| Role | Permissões |
|------|-----------|
| ROLE_ADMIN | Acesso total |
| ROLE_PROFESSOR | Leitura e cadastro de alunos |
| ROLE_ALUNO | Somente leitura |

## 📋 Endpoints principais

### Auth
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| POST | /auth/login | Realiza login | Público |
| POST | /auth/cadastro | Cadastra usuário | Público |

### Alunos
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| GET | /alunos | Lista todos | Autenticado |
| GET | /alunos/{id} | Busca por ID | Autenticado |
| POST | /alunos | Cadastra aluno | ADMIN, PROFESSOR |
| PUT | /alunos/{id} | Atualiza aluno | ADMIN, PROFESSOR |
| DELETE | /alunos/{id} | Remove aluno | ADMIN |

### Cursos
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| GET | /cursos | Lista todos | Autenticado |
| GET | /cursos/{id} | Busca por ID | Autenticado |
| POST | /cursos | Cadastra curso | ADMIN |
| PUT | /cursos/{id} | Atualiza curso | ADMIN |
| DELETE | /cursos/{id} | Remove curso | ADMIN |

### Relatórios
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| GET | /relatorios | Gera relatório geral | ADMIN |

## 🗄️ Diagrama do banco de dados
```
┌─────────────┐       ┌─────────────┐       ┌─────────────┐
│   usuarios  │       │  matricula  │       │    curso    │
│─────────────│       │─────────────│       │─────────────│
│ id (PK)     │       │ id (PK)     │       │ id (PK)     │
│ email       │       │ aluno_id(FK)│──────▶│ nome        │
│ senha       │       │ curso_id(FK)│       │ descricao   │
│ role        │       │ status      │       │ cargaHoraria│
└─────────────┘       │ dataMatric. │       │ nivel       │
                      └─────────────┘       │ ativo       │
┌─────────────┐              │              │ dataCriacao │
│    aluno    │              │              └─────────────┘
│─────────────│              │
│ id (PK)     │◀─────────────┘
│ nome        │
│ email       │
│ matricula   │
└─────────────┘
```

## 🧪 Testes

Para rodar os testes:
```bash
./mvnw test
```

## 👤 Autor

**Enzo Anthony**  
Backend Developer | Java | Spring Boot | AWS  
[LinkedIn](https://www.linkedin.com/in/enzo-anthony/) | [GitHub](https://github.com/seu-usuario)