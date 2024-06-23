> Documentação em construção

#### Convenções 
https://google.github.io/styleguide/javaguide.html#:~:text=Package%20names%20use%20only%20lowercase,or%20com.example.deep_space%20.

# Order Microservice
API de gerenciamento de pedidos de um e-commerce.

> Esse projeto está sendo construindo enquanto estudo outras tecnologias. E mais, é um projeto no qual estou testando algumas formas diferentes de construir meus projetos. Logo, eu sei cada conceito que apliquei aqui, estou apenas pensando um pouco fora da caixa e testando. Afinal DDD, TDD, BBB (rsrs) e qualquer outra abordagem, NÃO SÃO BALAS DE PRATA!

## Funcionalidades principais
- **Criação de pedido**
- **Busca de pedidos com paginação e filtros**

## Sobre a arquitetura
Nessee projeto sigo uma arquitetura modular e levianamente purista, separando a camada que contém toda a lógica agnóstica do negócio e a parte que diz respeito à aplicação em si (o framework e suas nuances).


## Tópicos abordados nesse projeto
- Domain Driven Design (DDD)
- Test Driven Development (TDD)
- CQRS
- Sagas
  - Orquestração

## Tecnologias
Spring Framework
Spring Boot
Spring Data JPA
Spring Data Mongo
Spring RabbitMQ
Flyway
Docker
Junit
Mockito