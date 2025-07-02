Sistema de Gestão - Java e JDBC (Prova nr1)
Sistema backend para controle de pessoas, funcionários e projetos usando Java e JDBC com MySQL. Este projeto visa demonstrar a integração entre Java e um banco de dados MySQL por meio de operações básicas de CRUD.

O que você vai precisar
Java JDK 11 ou versão superior

MySQL rodando localmente ou em servidor

MySQL Connector/J (driver JDBC)

Preparando o ambiente
Banco de Dados
Crie o banco de dados chamado provaDB no MySQL.

Caso tenha um arquivo .sql, basta importá-lo para gerar as tabelas e inserir dados de exemplo.

Configuração da Conexão
Abra o arquivo src/br/com/empresa/util/Conexao.java.

Verifique e ajuste as informações de conexão com o banco:

URL: jdbc:mysql://localhost:3306/provaDB

Usuário: root

Senha: (deixe em branco)

Instalando o Driver JDBC
Baixe o MySQL Connector/J (driver JDBC) no site oficial.

Adicione o arquivo .jar como dependência no IntelliJ:

Vá em File > Project Structure > Modules > Dependencies.

Clique no ícone de + e escolha JARs or Directories para incluir o arquivo JAR.

Como rodar o projeto
Execute o método main da classe src/br/com/empresa/app/App.java.

Os resultados dos testes aparecerão no console, incluindo as operações de CRUD feitas no banco de dados, como inserção, leitura, atualização e exclusão.
