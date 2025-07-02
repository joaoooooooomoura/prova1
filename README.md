# 🚀 Sistema de Gestão - Java e JDBC

Este projeto é uma aplicação backend desenvolvida em Java com JDBC para gerenciar pessoas, funcionários e projetos utilizando um banco de dados MySQL. Ideal para quem está aprendendo a integração entre Java e banco de dados.

## 📝 Requisitos

Antes de começar, você vai precisar ter os seguintes requisitos instalados:

- **Java JDK 11 ou versão superior**
- **MySQL Server**
- **MySQL Connector/J (driver JDBC)**

## ⚙️ Configuração

### Banco de Dados 🗄️

1. Crie um banco de dados chamado "provaDB" no MySQL.
2. Importe o arquivo "empresa.sql" para popular o banco de dados com as tabelas e dados de exemplo.

### Configuração de Conexão 🔌

1. Abra o arquivo "src/br/com/joao_prova/util/Conexao.java".
2. Verifique as configurações de conexão:
    - **URL**: "jdbc:mysql://localhost:3306/provaDB"
    - **Usuário**: "root"
    - **Senha**: ""

### Adicionando o Driver JDBC 📦

1. Baixe o **MySQL Connector/J** no site oficial.
2. No IntelliJ, adicione o arquivo `.jar` como dependência:
    - Vá em `File > Project Structure > Modules > Dependencies`.
    - Clique em `+` e escolha `JARs or Directories` para adicionar o arquivo JAR.

## 🚀 Executando o Projeto

Para rodar o projeto, basta seguir os passos abaixo:

1. Execute o método "main" da classe "src/br/com/empresa/app/App.java".
2. O console mostrará os resultados dos testes, que incluem as operações de:
   - Inserção de pessoas
   - Manipulação de dados de funcionários
   - Gerenciamento de projetos
