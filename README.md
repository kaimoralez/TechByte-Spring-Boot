# Techbyte API – Catálogo de Produtos

API REST desenvolvida em Java com Spring Boot para gerenciar produtos eletrônicos e periféricos. Usa Spring Data JPA para persistência em banco de dados MySQL.

## Sumário

- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Banco de dados](#banco-de-dados)
- [Persistência e chave primária](#persistência-e-chave-primária)
- [Rotas da API](#rotas-da-api)
- [Exemplos de uso](#exemplos-de-uso)
- [Como executar](#como-executar)

## Tecnologias

- Java 17 ou superior
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL 8
- Jackson
- Postman para testar a API

## Arquitetura

- **Entidade (`Product`):** representa os dados do produto e é mapeada para uma tabela no MySQL.
- **Repositório (`ProductRepository`):** estende `JpaRepository` e fornece operações como `findAll()`, `save()` e `saveAll()`.
- **Controller:** recebe as requisições HTTP e encaminha as operações para o repositório por injeção de dependência.
- **Jackson:** converte os dados JSON das requisições em objetos Java e serializa as respostas para JSON.

## Banco de dados

Configure a conexão em `src/main/resources/application.properties`:

```properties
spring.application.name=techbyte
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/produtos
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Crie o banco de dados antes de iniciar a aplicação:

```sql
CREATE DATABASE produtos;
```

- `spring.datasource.url`: endereço da instância MySQL e nome do banco.
- `spring.jpa.hibernate.ddl-auto`: atualiza a estrutura das tabelas conforme o mapeamento das entidades.
- `spring.jpa.show-sql` e `spring.jpa.properties.hibernate.format_sql`: exibem as consultas SQL no terminal.

## Persistência e chave primária

A entidade `Product` usa a estratégia `IDENTITY` para gerar o identificador:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

Essa estratégia usa o recurso `AUTO_INCREMENT` do MySQL para gerar os IDs.

## Rotas da API

| Método | Endpoint | Descrição | Corpo da requisição |
| --- | --- | --- | --- |
| `GET` | `/get` | Lista todos os produtos | Nenhum |
| `POST` | `/post` | Cadastra um produto | Objeto JSON |
| `POST` | `/itens` | Cadastra vários produtos em lote | Array JSON |

## Exemplos de uso

A API fica disponível em `http://localhost:8080`.

### Listar produtos

```http
GET http://localhost:8080/get
```

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "nome": "Teclado",
    "descricao": "mecanico",
    "preco": 200.0
  }
]
```

### Cadastrar um produto

Envie o cabeçalho `Content-Type: application/json` e o seguinte corpo para `POST http://localhost:8080/post`:

```json
{
  "nome": "Teclado",
  "descricao": "mecanico",
  "preco": 200.0
}
```

### Cadastrar produtos em lote

Envie o cabeçalho `Content-Type: application/json` e um array JSON para `POST http://localhost:8080/itens`:

```json
[
  {
    "nome": "Fone",
    "descricao": "preto",
    "preco": 10.0
  },
  {
    "nome": "Fone",
    "descricao": "branco",
    "preco": 15.0
  },
  {
    "nome": "Cabo",
    "descricao": "nylon",
    "preco": 22.0
  }
]
```

## Como executar

1. Clone o repositório e acesse a pasta do projeto:

   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
   cd NOME_DO_REPOSITORIO
   ```

2. Crie o banco de dados:

   ```sql
   CREATE DATABASE produtos;
   ```

3. Configure o usuário e a senha do MySQL em `src/main/resources/application.properties`.

4. Inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   Ou execute a classe `TechbyteApplication.java` pela sua IDE.
