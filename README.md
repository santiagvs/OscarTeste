# Teste Oscar

Este projeto é um app em Spring Boot e Java (versão 17), utilizando o PostgreSQL como banco de dados, para criar, ler, atualizar e deletar dados de calçados cadastrados. Ele permite gerenciar informações sobre modelos de calçados, incluindo nome, marca e categoria.

## Executando a aplicação

Primeiro, clone este repositório na sua máquina através do Git por meio do comando `git clone`:

```bash
git clone git@github.com:santiagvs/OscarTeste.git

# ou

git clone https://github.com/santiagvs/OscarTeste.git
```

Para executar a aplicação, basta executar o seguinte comando no diretório raiz do projeto:

```bash
mvn spring-boot:run
```

## Configuração do Banco de Dados

O projeto utiliza o PostgreSQL como banco de dados. Deve-se verificar se há um banco PostgreSQL rodando na máquina (a configuração da porta e do nome do banco é definida na propriedade `spring.datasource.url` em `application.properties`). Também deve-se configurar as informações sensíveis (usuário e senha do banco) de configuração no arquivo `secrets.properties` em `src/main/resources`.

```application.properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Requisições

Para fazer as requisições, é recomendado usar algum client HTTP para fazer as requests, como o **Postman**, **Insomnia** ou outros.

## Endpoints da API

>**Observação:** nesta aplicação, as rotas de verbo POST e PUT precisam ter valores em **JSON** informados no corpo da requisição (_request body_) referentes ao nome dos campos das tabelas. Por isso é extremamente recomendável que se use algum dos clients HTTP mencionados na seção de "Requisições".

------------------------

**Endpoints disponíveis na API:**

### Marca

| Método | Endpoint           | Descrição                                    |
|--------|--------------------|----------------------------------------------|
| GET    | `/marca`           | Lista todas as marcas.                       |
| GET    | `/marca/{id}`      | Obtém informações de uma marca por ID.       |
| POST   | `/marca`           | Cria uma nova marca.                         |
| PUT    | `/marca/{id}`      | Atualiza informações de uma marca.           |
| DELETE | `/marca/{id}`      | Deleta uma marca por ID.                     |

#### Exemplo de criação de uma Marca

```json
{
  "nome": "Marca X"
}
```

### Categoria

| Método | Endpoint             | Descrição                                  |
|--------|----------------------|--------------------------------------------|
| GET    | `/categoria`         | Lista todas as categorias.                 |
| GET    | `/categoria/{id}`    | Obtém informações de uma categoria por ID. |
| POST   | `/categoria`         | Cria uma nova categoria.                   |
| PUT    | `/categoria/{id}`    | Atualiza informações de uma categoria.     |
| DELETE | `/categoria/{id}`    | Deleta uma categoria por ID.               |

#### Exemplo de criação de uma Categoria

```json
{
  "nome": "Sapatos"
}
```

### Modelo

| Método | Endpoint                  | Descrição                             |
|--------|---------------------------|---------------------------------------|
| GET    | `/modelo`                 | Lista todos os modelos de calçados.   |
| GET    | `/modelo/{id}`            | Obtém informações de um modelo por ID.|
| GET    | `/modelo?nome={nome}`     | Lista modelos por nome da marca.      |
| GET    | `/modelo?categoriaId={id}`| Lista modelos por ID da categoria.    |
| POST   | `/modelo`                 | Cria um novo modelo de calçado.       |
| PUT    | `/modelo/{id}`            | Atualiza informações de um modelo.    |
| DELETE | `/modelo/{id}`            | Deleta um modelo de calçado por ID.   |

#### Exemplo de criação de um Modelo

```json
{
  "nome": "Grand Court",
  "marca_id": 1,
  "categoria_id": 2
}
```

### Cor

> Obs: esta tabela em questão já vem **_seedada_** por definição, no diretório `config`, arquivo CorConfig.java. Ao inicializar a aplicação pela primeira vez, quando o Hibernate realiza a criação das tabelas, Cor já tem sua tabela populada com alguns registros.

| Método | Endpoint      | Descrição                               |
|--------|---------------|-----------------------------------------|
| GET    | `/cor`        | Lista todas as cores.                   |
| GET    | `/cor/{id}`   | Obtém informações de uma cor por ID.    |
| POST   | `/cor`        | Cria uma nova cor.                      |
| PUT    | `/cor/{id}`   | Atualiza informações de uma cor.        |
| DELETE | `/cor/{id}`   | Deleta uma cor por ID.                  |

#### Exemplo de criação de uma Cor

```json
{
  "nome": "Marsala"
}
```

### Produto

| Método | Endpoint         | Descrição                                   |
|--------|------------------|---------------------------------------------|
| GET    | `/produtos`      | Lista todos os produtos.                    |
| GET    | `/produto?params`| Filtra produtos com base em parâmetros.     |
| GET    | `/produto/{id}`  | Obtém informações de um produto por ID.     |
| POST   | `/produto`       | Cria um novo produto.                       |
| PUT    | `/produto/{id}`  | Atualiza informações de um produto por ID.  |
| DELETE | `/produto/{id}`  | Deleta um produto por ID.                   |

Esse é o cerne central da aplicação. O mais importante é a rota `/produto?query`.

Ao utilizar a rota`/produto` com o método GET, você pode passar os seguintes parâmetros via _query params_ para filtrar os produtos de acordo com os argumentos passados:

| Parâmetro     | Tipo      | Descrição                                        |
|---------------|-----------|--------------------------------------------------|
| tamanho       | Integer   | Filtra produtos pelo tamanho especificado.       |
| categoria     | String    | Filtra produtos pela categoria especificada.     |
| corId         | Long      | Filtra produtos pela cor através do ID da cor.   |
| precoMin      | Double    | Filtra produtos com preço igual ou acima de.     |
| precoMax      | Double    | Filtra produtos com preço igual ou abaixo de.    |
| marca         | String    | Filtra produtos pelo nome da marca especificada. |
| modelo        | String    | Filtra produtos pelo nome do modelo especificado.|

Exemplo:

```HTTP
GET /produto?tamanho=40&corId=1&precoMin=50.0&marca=Nike
```

De acordo com essa configuração, buscará por todos os produtos/calçados que possuem tamanho 40, a cor de id 1, preço mínimo de 50 reais e que a marca seja Nike, caso existam.

#### Exemplo de criação de um Produto

```json
{
  "modelo_id": 3,
  "marca_id": 1,
  "categoria_id": 2,
  "cor_id": 9,
  "tamanho": 40,
  "quantidadeEstoque": 200,
  "preco": 299.99
}
```
