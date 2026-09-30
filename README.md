# Spring AI Ollama Agent

A Spring Boot customer-support chat application powered by Spring AI and a locally running Ollama model. The assistant can look up and cancel customer orders and answer inventory questions using data stored in PostgreSQL. A browser-based chat interface is served by the application.

## Features

- Chat with a customer-support assistant through the web UI or `POST /chat`.
- Look up an order's status, cancel an order, or ask for the total order count.
- Check a product's stock, list products currently in stock, or get the total number of inventory units.
- Keep chat context between requests using a conversation ID; the in-memory chat history retains up to 10 messages per conversation.
- Store orders and inventory in PostgreSQL. The application initializes the schema and inserts sample data on startup.

## Technology

- Java 21
- Spring Boot 4.1.1 and Spring MVC
- Spring AI 2.0.1 with the Ollama chat model (`llama3.2:3b` by default)
- Spring Data JPA / Hibernate
- PostgreSQL (runtime); H2 for tests
- Maven Wrapper
- Static HTML/CSS/JavaScript chat interface (Tailwind CSS is loaded from its CDN)

## Prerequisites

Install or run:

1. Java 21
2. PostgreSQL, with a database named `products`
3. Ollama, with the configured model downloaded:

   ```powershell
   ollama pull llama3.2:3b
   ```

   Ollama must be reachable at `http://localhost:11434`.

The application connects to PostgreSQL at `jdbc:postgresql://localhost:5432/products`. Configure database credentials with `DB_USERNAME` and `DB_PASSWORD`. The current defaults are `postgres` and `admin`; use appropriate credentials for your environment.

For example, in PowerShell:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your-password"
```

## Run locally

Start PostgreSQL and Ollama, then run the application from the repository root:

```powershell
.\mvnw.cmd spring-boot:run
```

Open [http://localhost:8081](http://localhost:8081) to use the chat UI. The server listens on port `8081`.

To build the application:

```powershell
.\mvnw.cmd clean package
```

To run the tests:

```powershell
.\mvnw.cmd test
```

The persistence-layer tests use an in-memory H2 database configured in `src/test/resources/application.yml`.

## Chat API

### `POST /chat`

Sends a plain-text message to the assistant and returns its response as plain text.

Required headers:

| Header | Value |
| --- | --- |
| `Content-Type` | `text/plain` |
| `Conversation-Id` | An identifier shared by requests in the same conversation |

Example:

```powershell
curl.exe -X POST "http://localhost:8081/chat" `
  -H "Content-Type: text/plain" `
  -H "Conversation-Id: demo-conversation" `
  --data "What is the status of order 1042?"
```

Use the same `Conversation-Id` for follow-up messages to retain chat context. Use a new ID to start a separate conversation.

## Assistant capabilities

The model can call the following application tools when relevant:

| Capability | Behavior |
| --- | --- |
| Order status | Find an order by ID and return its current status |
| Cancel order | Set an existing order's status to `Cancelled - cancelled by customer` |
| Order count | Return the number of stored orders |
| Check stock | Find a product by its exact name and return its quantity |
| In-stock products | List product names and quantities for items with a quantity greater than zero |
| Total inventory | Sum the quantities of all inventory items |

Sample order IDs and products are inserted from `src/main/resources/data.sql`; sample records include order IDs `1042`–`1045`. Seed inserts use conflict handling so existing records are not duplicated.

## Project structure

```text
src/main/java/com/shalinidev/spring/ai/agent/
  config/       Chat client, model tools, and conversation memory configuration
  controller/   HTTP chat endpoint
  model/        JPA entities for orders and inventory
  repository/   Spring Data repositories
  service/      Chat, order, and inventory business logic
  tools/        Functions exposed to the AI model
src/main/resources/
  application.yml   Server, PostgreSQL, and Ollama configuration
  schema.sql        Database table definitions
  data.sql          Idempotent sample data
  static/index.html Browser chat interface
```

## Configuration

The main settings are in `src/main/resources/application.yml`:

- Server port: `8081`
- PostgreSQL URL: `jdbc:postgresql://localhost:5432/products`
- Ollama URL: `http://localhost:11434`
- Chat model: `llama3.2:3b`
- Hibernate schema handling: `update`
- SQL initialization: enabled on startup

Change these values there or adapt the configuration to your deployment environment. Do not use the sample database credentials in production.
