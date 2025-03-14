# dot-hours-api
# Sistema de Gerenciamento de Projetos e Lançamento de Horas

Este é um sistema desenvolvido para gerenciar projetos, atividades e o lançamento de horas trabalhadas. O sistema permite cadastrar usuários (administrador e usuário comum), gerenciar projetos e atividades, realizar o lançamento de horas e também oferece funcionalidades para listar, editar, visualizar e filtrar essas informações.

## Tecnologias Utilizadas

- **Backend:** Java, Spring Boot
- **Frontend:** Angular
- **Banco de Dados:** MySQL
- **Autenticação e Autorização:** JWT (JSON Web Token)
- **Testes de API:** Postman

## Funcionalidades

### Gestão de Usuários:
- Cadastro de usuários com diferentes permissões (Administrador ou Usuário Comum)
- Edição e visualização de usuários

### Gestão de Projetos:
- Cadastro de projetos
- Edição de projetos
- Visualização de detalhes do projeto
- Filtragem de projetos

### Gestão de Atividades:
- Cadastro de atividades relacionadas a projetos
- Edição de atividades
- Visualização de atividades
- Filtragem de atividades

### Gestão de Lançamento de Horas:
- Lançamento de horas trabalhadas em atividades específicas
- Edição e visualização de horas lançadas
- Filtragem de lançamentos de horas

### Autenticação e Autorização:
- Login de usuários
- Controle de acesso com JWT (usuários comuns e administradores)

## Requisitos

Antes de rodar a aplicação, certifique-se de ter os seguintes pré-requisitos instalados:

- **JDK 17 ou superior**
- **Node.js** e **npm**
- **MySQL** configurado e rodando
- **Angular CLI**

## Como Rodar a Aplicação

### Backend (Java Spring Boot)

1. Clone o repositório:
   ```bash
   git clone git@github.com:JoaoVictorBES/dot-hours-api.git
Configure a conexão com o banco de dados MySQL no arquivo application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_gerenciamento
spring.datasource.username=usuario
spring.datasource.password=senha


Execute o backend:
./mvnw spring-boot:run


Instale as dependências:
npm install

Rode a aplicação Angular:
ng serve

Acesse o frontend no navegador em http://localhost:4200.

Testando a API com Postman
Importe a coleção Postman fornecida na pasta postman para testar as rotas da API.
Realize os testes de autenticação, cadastro de usuários, projetos, atividades e lançamentos de horas.
Como Contribuir
Se você deseja contribuir com melhorias ou correções, sinta-se à vontade para fazer um fork do repositório, criar sua branch de alterações e enviar um pull request.

Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
