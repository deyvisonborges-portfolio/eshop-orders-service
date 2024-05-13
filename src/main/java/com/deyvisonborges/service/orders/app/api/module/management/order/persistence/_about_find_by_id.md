Em um ambiente de persistência de dados com Hibernate (ou JPA em geral), é comum usar transações para controlar as operações de leitura e gravação no banco de dados. Uma transação é uma unidade de trabalho que consiste em uma ou mais operações de banco de dados que devem ser executadas como uma única unidade, ou seja, todas as operações devem ser concluídas com sucesso ou nenhuma delas deve ser aplicada.

Quando você trabalha com o Hibernate, ele gerencia uma "sessão" para você. Uma sessão do Hibernate é uma interface entre sua aplicação e o banco de dados, e é responsável por rastrear todas as operações de persistência de objetos, consultas e transações.

No seu código, ao acessar uma coleção de uma entidade JPA fora de uma transação, você pode receber uma exceção de LazyInitializationException. Isso ocorre porque o Hibernate tenta carregar a coleção do banco de dados, mas a sessão do Hibernate já foi fechada (ou nunca foi aberta) quando você tentou acessar a coleção.

Ao "iniciar uma transação antes de acessar a coleção", significa que você envolve a operação de acesso à coleção dentro de uma transação gerenciada pelo Hibernate. Quando você faz isso:

java
Copiar código
```java
@Transactional
public Optional<Order> findById(String id) {
    final var order = this.jpaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Not found order with id " + id));
    return Optional.of(OrderJPAEntity.toAggregate(order));
}
```
Você está usando a anotação @Transactional do Spring. Essa anotação informa ao Spring que o método findById deve ser executado dentro de uma transação. O Spring cuida da abertura e fechamento da transação para você.

Quando o método findById é chamado, o Spring abre uma transação antes da execução do método e fecha a transação após a conclusão do método. Durante a execução do método dentro dessa transação, a sessão do Hibernate permanece aberta, permitindo o acesso à coleção sem gerar a exceção de LazyInitializationException.

Em resumo, iniciar uma transação antes de acessar a coleção garante que a sessão do Hibernate esteja aberta durante o acesso à coleção, permitindo que você acesse os dados sem encontrar problemas de sessão fechada. Isso é importante para evitar exceções e garantir o comportamento esperado ao trabalhar com o Hibernate ou qualquer outro framework JPA.