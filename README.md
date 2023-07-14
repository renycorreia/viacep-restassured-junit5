# Projeto de Testes
## API Via CEP - Bibliotecas RestAssured e Junit5

Projeto de desenvolvimento de testes  na API Via CEP, que fornece informações de endereço com base em um CEP, utilizando as bibliotecas RestAssured e Junit5.

### Instalação e Configuração

Para executar os testes deste projeto, siga as etapas abaixo:

1. Clone este repositório para o seu ambiente local.
2. Certifique-se de ter o Java JDK instalado em seu sistema.
3. Instale as dependências necessárias executando o comando `mvn install` na raiz do projeto.
4. Abra o arquivo `src/test/java/TestCases.java` para visualizar e executar os testes.

### Estrutura do Projeto

O projeto possui a seguinte estrutura de diretórios:

```
- src
  - test
    - java
      - GetCepTests.java
    - resources
      - viacep.json
```

O arquivo `GetCepTests.java` contém os casos de teste implementados utilizando a biblioteca Junit5 e o RestAssured para fazer as requisições à API.

### API Utilizada

Este projeto utiliza a API ViaCEP para realizar o desafio. A documentação oficial da API pode ser encontrada em [https://viacep.com.br](https://viacep.com.br). A URL base da API é [https://viacep.com.br/ws/CEP/json](https://viacep.com.br/ws/CEP/json).

