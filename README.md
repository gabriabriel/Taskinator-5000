# Taskinator 5000

API REST para gerenciamento de tarefas pessoais, desenvolvida com Java e Spring Boot.

## Sobre o projeto

O **Taskinator 5000** é uma API REST para gerenciamento de tarefas pessoais.

A aplicação permite criar, consultar, atualizar, concluir e excluir tarefas, além de organizá-las por categorias e prioridades.

O projeto também possui um sistema de **lembretes automáticos por e-mail**. O usuário pode definir uma data e hora para um lembrete e, quando esse horário é atingido, a aplicação envia automaticamente uma notificação por e-mail.

O projeto foi desenvolvido com foco no aprendizado e aplicação prática de conceitos de desenvolvimento de APIs REST, arquitetura em camadas, persistência de dados, validação, integração com serviços externos e documentação de APIs.

---

## Funcionalidades

### Tarefas

- Criar tarefas
- Listar todas as tarefas
- Buscar tarefa por ID
- Atualizar parcialmente uma tarefa
- Excluir tarefas
- Concluir tarefas
- Reabrir tarefas
- Filtrar tarefas por status
- Filtrar tarefas por prioridade
- Filtrar tarefas por categoria
- Listar tarefas próximas do vencimento
- Ordenar tarefas pela data de criação

### Categorias

- Criar categorias
- Listar categorias
- Buscar categoria por ID
- Atualizar categorias
- Excluir categorias

### Lembretes por e-mail

- Definir data e hora para um lembrete
- Enviar automaticamente um e-mail quando o horário do lembrete for atingido
- Permitir o reagendamento de lembretes
- Cancelar o envio de lembretes quando uma tarefa é concluída

### Documentação

- Documentação da API utilizando Swagger/OpenAPI
- Descrição dos endpoints
- Descrição dos parâmetros
- Exemplos de requisições e respostas
- Documentação dos códigos de resposta HTTP

---

## Tecnologias utilizadas


- Java 24
- Spring Boot
- Spring Data JPA 
- Hibernate 
- MySQL
- Maven 
- Resend 
- Swagger / OpenAPI 
- Git / GitHub 

---

## Arquitetura e estrutura do projeto

O projeto utiliza uma arquitetura baseada na separação de responsabilidades entre diferentes camadas.

```text
src/
└── main/
    ├── java/com/taskinator5000_api/
    │   ├── config/
    │   ├── controller/
    │   ├── dto/
    │   │   ├── request/
    │   │   └── response/
    │   ├── entity/
    │   ├── enums/
    │   ├── exception/
    │   ├── mapper/
    │   ├── repository/
    │   └── service/
    │
    └── resources/
        └── application.properties
```

### Principais camadas

#### Controller

Responsável por receber as requisições HTTP, validar os dados de entrada e encaminhar as operações para a camada de serviço.

#### Service

Contém as principais regras de negócio da aplicação, como criação e atualização de tarefas, conclusão e reabertura de tarefas, validações e processamento dos lembretes.

#### Repository

Responsável pelo acesso e persistência dos dados no banco de dados através do Spring Data JPA.

#### Entity

Representa as entidades persistidas no banco de dados.

Atualmente, as principais entidades são:

- `Task`
- `Category`

#### DTO

Os DTOs são utilizados para definir os dados recebidos e retornados pela API, evitando que as entidades sejam expostas diretamente nos endpoints.

Os DTOs são divididos em:

- `request` — dados recebidos pela API
- `response` — dados retornados pela API

#### Mapper

Responsável pela conversão entre entidades e DTOs.

#### Config

Contém configurações da aplicação, incluindo a configuração do OpenAPI/Swagger.

---

## Pré-requisitos

Para executar o projeto localmente, é necessário ter instalado:

- Java 24
- MySQL
- Git
- Uma conta no [Resend](https://resend.com/) para utilizar o sistema de lembretes por e-mail

O projeto utiliza o **Maven Wrapper**, portanto não é necessário instalar o Maven separadamente.

---

## Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/gabriabriel/Taskinator-5000.git
```

Entre no diretório:

```bash
cd Taskinator-5000
```

### 2. Crie o banco de dados

No MySQL, crie o banco utilizado pela aplicação:

```sql
CREATE DATABASE taskinator;
```

### 3. Configure as variáveis de ambiente

O projeto utiliza variáveis de ambiente para evitar que credenciais e chaves de API sejam armazenadas diretamente no código-fonte.

Utilize o arquivo `.env.example` como referência para criar um arquivo `.env`.

As variáveis necessárias são:

```env
DB_USERNAME=
DB_PASSWORD=
RESEND_API_KEY=
TASKINATOR_EMAIL=
```

#### Variáveis

| Variável | Descrição |
|---|---|
| `DB_USERNAME` | Usuário do banco de dados MySQL |
| `DB_PASSWORD` | Senha do banco de dados MySQL |
| `RESEND_API_KEY` | Chave da API do Resend |
| `TASKINATOR_EMAIL` | E-mail utilizado pelo sistema para envio dos lembretes |

> **Importante:** o arquivo `.env` contém informações sensíveis e não deve ser enviado para o GitHub.

---

## Como executar

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada, por padrão, em:

```text
http://localhost:8080
```

---

# Configuração do Resend

O Taskinator 5000 utiliza o **Resend** para realizar o envio dos lembretes por e-mail.

O Resend é um serviço de envio de e-mails voltado para aplicações e disponibiliza uma API e SDKs para diferentes linguagens, incluindo Java.

### Como o Resend é utilizado no projeto

O fluxo de funcionamento é:

```text
Tarefa
   │
   ├── reminderAt
   │
   ▼
ReminderService
   │
   │ verifica lembretes pendentes
   ▼
EmailService
   │
   │ utiliza Resend Java SDK
   ▼
Resend API
   │
   ▼
E-mail do destinatário
```

O `ReminderService` é responsável por verificar os lembretes que chegaram ao horário programado.

Quando encontra um lembrete válido, ele utiliza o `EmailService`, que é responsável pela comunicação com o Resend.

Dessa forma, a lógica de negócio relacionada aos lembretes fica separada da implementação responsável pelo envio dos e-mails.

### 1. Criar uma conta no Resend

Acesse:

https://resend.com/

Crie uma conta e acesse o painel do Resend.

### 2. Criar uma API Key

No painel do Resend, acesse a área de **API Keys** e crie uma nova chave.

Para uma aplicação que somente precisa enviar e-mails, pode ser utilizada uma chave com permissão de envio (`Sending access`) em vez de uma chave com acesso completo.

### 3. Configurar a API Key

No arquivo `.env`:

```env
RESEND_API_KEY=re_sua_chave_aqui
```

A aplicação utiliza essa variável através de:

```properties
resend.api-key=${RESEND_API_KEY}
```

### 4. Configurar o e-mail

No `.env`, informe o endereço utilizado pelo sistema:

```env
TASKINATOR_EMAIL=seu-email@exemplo.com
```

Esse endereço é utilizado pela aplicação para o envio dos lembretes.

### 5. Restrições do ambiente de testes

Durante o desenvolvimento, o Resend pode restringir os destinatários de e-mails de teste ao próprio endereço da conta.

Para enviar e-mails para outros destinatários, é necessário configurar e verificar um domínio no Resend e utilizar um endereço desse domínio como remetente.

Para um ambiente de produção, recomenda-se configurar um domínio próprio e realizar a verificação solicitada pelo Resend.

### 6. Testando a integração

Depois de configurar a API Key e iniciar a aplicação, crie uma tarefa com um lembrete:

```json
{
  "title": "Testar lembrete",
  "description": "Verificar o envio automático do e-mail.",
  "priority": "HIGH",
  "dueDate": "2026-08-25",
  "reminderAt": "2026-08-25T18:00:00",
  "categoryId": 1
}
```

Quando o horário definido em `reminderAt` for atingido, o `ReminderService` identificará a tarefa e solicitará ao `EmailService` que envie a notificação através do Resend.

---

# Documentação da API

A API possui documentação interativa utilizando **Swagger/OpenAPI**.

Depois de iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

A interface permite:

- visualizar todos os endpoints;
- consultar os parâmetros;
- visualizar os códigos de resposta;
- consultar os schemas dos DTOs;
- visualizar exemplos;
- executar requisições diretamente pelo navegador.

A especificação OpenAPI também pode ser acessada em:

```text
http://localhost:8080/v3/api-docs
```

---

## Principais endpoints

### Categorias

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/categorias` | Criar categoria |
| GET | `/categorias` | Listar categorias |
| GET | `/categorias/{id}` | Buscar categoria |
| PUT | `/categorias/{id}` | Atualizar categoria |
| DELETE | `/categorias/{id}` | Excluir categoria |

### Tarefas

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/tarefas` | Criar tarefa |
| GET | `/tarefas` | Listar tarefas |
| GET | `/tarefas/{id}` | Buscar tarefa |
| PATCH | `/tarefas/{id}` | Atualizar tarefa |
| DELETE | `/tarefas/{id}` | Excluir tarefa |
| PATCH | `/tarefas/{id}/concluir` | Concluir tarefa |
| PATCH | `/tarefas/{id}/reabrir` | Reabrir tarefa |
| GET | `/tarefas/status/{status}` | Filtrar por status |
| GET | `/tarefas/prioridade/{priority}` | Filtrar por prioridade |
| GET | `/tarefas/categorias/{categoryId}` | Filtrar por categoria |
| GET | `/tarefas/vencimento` | Listar tarefas por vencimento |
| GET | `/tarefas/criadas` | Ordenar por criação |

---

## Exemplos

### Criar uma categoria

```http
POST /categorias
```

```json
{
  "name": "Estudos"
}
```

### Criar uma tarefa

```http
POST /tarefas
```

```json
{
  "title": "Estudar Spring Boot",
  "description": "Estudar documentação do Spring Boot e implementar novos endpoints.",
  "priority": "HIGH",
  "dueDate": "2026-08-25",
  "reminderAt": "2026-08-25T18:00:00",
  "categoryId": 1
}
```

### Concluir uma tarefa

```http
PATCH /tarefas/1/concluir
```

### Reabrir uma tarefa

```http
PATCH /tarefas/1/reabrir
```

---

# Segurança

O projeto utiliza variáveis de ambiente para evitar o armazenamento de informações sensíveis no código-fonte.

Entre as informações protegidas estão:

- senha do banco de dados;
- chave da API do Resend.

O arquivo `.env` está incluído no `.gitignore` e não deve ser versionado.

O arquivo `.env.example` é disponibilizado apenas como modelo das variáveis necessárias.

A API Key do Resend também deve ser mantida em segredo. Para aplicações que precisam apenas enviar e-mails, recomenda-se utilizar uma chave com permissões restritas de envio sempre que possível.

---

# Autor

**Gabriel Rocha**

Projeto desenvolvido para prática e aprofundamento em desenvolvimento de APIs REST com Java e Spring Boot.
