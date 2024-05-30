# E-commerce-PetBits

## Descrição

E-commerce-PetBits é um projeto de comércio eletrônico focado em produtos para animais de estimação. Este projeto foi criado por Alex Trauthman.

## Tecnologias Usadas

- Java
- PostgreSQL
- Docker
- Quarkus
- PgAdmin

## Recursos

- Sistema de gerenciamento de produtos.
- Produtos: Ração, Petiscos, Remédios e Brinquedos.
- Inserção de dados sobre o alvo do produto.

## Como Usar?
- Execute a aplicação com o comando " ./mvnw compile quarkus:dev ".
- Vá em SwaggerUI e você poderá usar os caminhos. 
- Alguns caminhos são exclusivos para Clientes ou Administradores, para acessa-los vá em AuthUser e insira um usuário, coloque id 1 para cliente e 2 para administrador. No importSql são "MOCKADOS" um administrador e um funcionário, 
- Administrador : nome: "jonata", senha: "senha" perfil:2.
- Cliente : nome : "cleiton", senha: "senhassa" perfil:1.
- Copie a autorização gerada e coloque sem espaços em "Authorize", após isso você poderá usar os caminhos daquele determinado perfil de usuário durante 24 horas (caso não haja interrupções no código (quarkus,postgre ou browser)).