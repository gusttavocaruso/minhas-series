<div align="center">

# "Gerenciando as minhas séries"

Ao clonar o repositório para sua máquina, instale as dependencias através do comando `mvn install`

---
</div>

### Requisitos do Projeto

Imagina saber exatamente quais séries você já assistiu ou até mesmo aquelas que você mais gostou e poderia rever. Quem sabe  ter o  registro de  quantos capítulos tem uma nova série e até mesmo a quantidade de minutos que vai levar para terminá-la... Calma, você não está dormindo no meio de uma série! 😆

Falando assim parece um sonho, mas é possível! Basta colocar em prática as habilidades de criação de aplicação REST, uso do Spring Data para persistência de dados, tratamento de exceções e deploy em servidores de nuvem, adquiridas nas aulas anteriores.

Então bora lá e nada de dormir no pontou ou quem sabe na série  🚀


### Descrição

Lili Elbe não está conseguindo gerenciar bem as séries que tem assistido , por muitas vezes, não lembra qual foi o  último episódio visto ou quais séries novas tem interesse em  maratonar.  

Nas últimas férias, Lili se perguntou quanto tempo gastou acompanhando os últimos lançamentos que estavam sendo tão aguardados por ela, e para que pudesse gerenciar melhor seu tempo dentro e fora das telas, decidiu criar uma aplicação que a ajude ter maior controle sobre as suas séries amadas ! 

No dia a dia de uma pessoa programadora, você terá que resolver diversos problemas e consequentemente ajudar muitas pessoas, que tal então começarmos a ajudar a Lili com a criação dessa aplicação? 💚

Primeiro vamos analisar as necessidades descritas por ela: 

- Gostaria de poder gerir as minhas séries da seguinte forma!

- Cadastrar e visualizar as séries que já assisti;
- Adicionar episódios a séries que já assisti (indicando o número do episódio e o tempo em minutos que ele possui);
- Remover série com os seus episódios;
- Visualizar episódios assistidos de uma determinada séries;
- Visualizar tempo gasto total de todos os episódios já assistidos em todas as séries."

Agora que você já sabe todas as necessidades de Lili, crie uma  API que possa efetuar tais operações seguindo as seguintes especificações:


#### Listagem de endpoints:
- Cadastro de série
Rota `/series`
Metodo: POST
Corpo da requisição:
  ```json
  {
      "nome":"Doctor Who"
  }
  ```
  Corpo da resposta:
  ```json
  {
      "id": 1,
      "nome": "Doctor Who",
      "episodios": []
  }
  ```

- Visualizar séries
Rota `/series`
Metodo: GET
Corpo da resposta:
  ```json
  [
      {
          "id": 1,
          "nome": "Doctor Who",
          "episodios": []
      },
      {
          "id": 2,
          "nome": "Friends",
          "episodios": []
      }
  ]
  ```

- Remover séries
Rota `/series/{serie_id}`
Metodo: DELETE

- Adicionar episódios
Rota `/series/{serie_id}/episodios`
- Metodo: POST
Corpo da requisição:
  ```json
  {
    "numero": 1,
    "duracaoEmMinutos": 60
  }
  ```
  Corpo da resposta:
  ```json
  {
      "id": 1,
      "nome": "Doctor Who",
      "episodios": [
          {
              "id": 2,
              "numero": 1,
              "duracaoEmMinutos": 60
          }
      ]
  }
  ```

- Visualizar episódios de uma série
Rota `/series/{serie_id}/episodios`
- Metodo: GET
Corpo da resposta:
  ```json
  [
    {
      "id": 2,
      "numero": 1,
      "duracaoEmMinutos": 60
    }
  ]
  ```

- Visualizar tempo gasto
Rota `/series/tempo`
- Metodo: GET
Corpo da resposta:
  ```json
  {
    "tempoEmMinutos": 600
  }
  ```

> Statuscode para casos de sucesso devem ser 200(OK)

#### Casos de Falha
- Cadastro de série com nome existente deve emitir a exceção `SerieExistenteException`
- Tentativas de acesso a uma série que não exista deve emitir a exceção `SerieNaoEncontradaException`
- Adição de episódios com o mesmo número para a mesma série deve emitir a exceção `EpisodioExistenteException`
- Casos de erro não mapeados neste documento devem emitir a exceção `ErroInesperadoException` 


#### Mapeamento de exception para statusCode

Utilize um `ControllerAdivice` para realizar o mapeamento das exceções conforme apresentado a seguir:  

Exception: SerieExistenteException
StatusCode: 409
Corpo da resposta:
```json
{
  "error": "Série Existente"
}
```

Excecption: EpisodioExistenteException
statusCode: 409
Corpo da resposta:
```json
{
  "error": "Episódio Existente"
}
```

Exception: SerieNaoEncontradaException
StatusCode: 404
Corpo da resposta:
```json
{
  "error": "Série não encontrada"
}
```

Exception: ErroInesperadoException
StatusCode: 500
Corpo da resposta:
```json
{
  "error": "Erro inesperado"
}
```

#### Tolerância a falhas:

Adicione um `CircuitBreaker` utilizando a biblioteca `resilience4j` no endpoint `/series/{serie_id}/episodios`, fazendo com que, caso haja 50% de erro nas requisições ,o serviço retorne:
StatusCode 503
Corpo da resposta:
```json
{
  "error": "Serviço temporariamente indisponível"
}
```

#### Testes

Realize testes de unidade para as classes de serviço e testes de integração para cada endpoint utilizando como ferramenta:
```java
  @Autowired
  private MockMvc mockMvc;
```

---

<div align="center">

Projeto realizado durante curso de JAVA promovido pela [Trybe](https://www.betrybe.com/) em parceria com a [CI&T](https://ciandt.com/br/pt-br/home) ©

<img src="https://avatars.githubusercontent.com/u/82593112?v=4" width="60px">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTU59qxQX-52fU_TNMxS_MkbhlgUyTkYDkkAzpSTyREZvLn6yrmbFc0I7wUWIq9IF0K1oI&usqp=CAU" width="60px">

</div>
