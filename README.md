<h1 align="center">Api Rest com Kotlin e Persistencia de Dados - DIO</h1>
<h4 align="center">Sistema de Analise de Solicitação de Crédito</h4>
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="Kotlin">
        <img src="https://img.shields.io/badge/Kotlin-v1.7.22-purple.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.7-green" />
    </a>
    <a alt="Gradle">
        <img src="https://img.shields.io/badge/Gradle-v8.1.1-yellowgreen" />
    </a>
    <a alt="H2 ">
        <img src="https://img.shields.io/badge/H2-v2.1.214-darkblue.svg" />
    </a>
    <a alt="Flyway">
        <img src="https://img.shields.io/badge/Flyway-v9.19.1-orange">
    </a>
    <a alt = Swagger >
        <img src="https://img.shields.io/badge/Swagger-v2.0.2-blueviolet">
    </a>
</p>

## 📋Descrição

O Credit Application System é uma API REST desenvolvida em Spring Boot e Kotlin para uma empresa de empréstimo realizar análise de solicitações de crédito. Essa API fornece funcionalidades para cadastrar clientes, gerenciar perfis, cadastrar e visualizar solicitações de empréstimo.

Este projeto foi desenvolvido como parte de um desafio proposto pela escola DIO (Digital Innovation One).

## 🖥️Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Gradle
- H2 Database (banco de dados em memória)
- Flyway  (controle de versão do banco de dados)
- Swagger

## 🛠️Funcionalidades

A API fornece as seguintes funcionalidades:

##### Cliente (Customer)

- Cadastrar
- Editar cadastro
- Visualizar perfil
- Deletar cadastro

##### Solicitação de Empréstimo (Credit)
✅ Desafio 1: o máximo de parcelas permitido será 48

✅ Desafio 2 : data da primeira parcela deverá ser no máximo 3 meses após o dia atual

- Cadastrar

- Listar todas as solicitações de empréstimo de um cliente

- Visualizar um empréstimo

  

## 🚀Executando o projeto

1. Certifique-se de ter o Kotlin e o Gradle instalados na sua máquina.
2. Clone este repositório.
3. Navegue até o diretório raiz do projeto.
4. Execute o comando `gradle bootRun` para iniciar a aplicação.

Para facilitar o desenvolvimento e interagir com o projeto, recomendo o uso de uma IDE, como o IntelliJ IDEA ou o Eclipse. Abra o projeto na sua IDE preferida para ter acesso a recursos avançados de depuração e execução simplificada.

Você pode acessar o banco de dados ou testar testar os endpoints da API utilizando o Postman ou o Swagger:

- Para acessar o banco de dados basta acessar a URL http://localhost:8080/h2-console digitando o usuário e a senha, ambas definidas como admin
- Para acessar a documentação e testar a API de forma interativa, execute o projeto e acesse a URL http://localhost:8080/swagger-ui/index.html
- Alternativamente, você pode utilizar o Postman para enviar requisições HTTP aos endpoints da API.

## 🧪Testes

Foram implementados testes unitários e de integração para as classes construídas. Execute o comando `gradle test` para rodar os testes.

## Banco de Dados

O projeto utiliza o banco de dados H2 para armazenar os dados. As migrações do banco de dados são gerenciadas pelo Flyway.

