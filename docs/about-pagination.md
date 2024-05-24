# Paginação e filtro com Spring Data JPA

Para nos ajudar a lidar com essa situação, Spring Data JPA fornece uma maneira de implementar a paginação com PagingAndSortingRepository .

PagingAndSortingRepositoryestende CrudRepository para fornecer métodos adicionais para recuperar entidades usando a abstração de paginação.

```java
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
  Page<T> findAll(Pageable pageable);
}
```

`findAll(Pageable pageable)`: retorna um Pagenúmero de entidades que atendem à condição de paginação fornecida pelo Pageableobjeto.

Spring Data também oferece suporte a muitas criações de consultas úteis a partir de nomes de métodos que usaremos para filtrar o resultado neste exemplo, como:

```java
Page<Tutorial> findByPublished(boolean published, Pageable pageable);
Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
```

Você pode encontrar mais palavras-chave suportadas dentro dos nomes dos métodos aqui .

Por exemplo: exemplo de consulta do repositório JPA

Para classificar vários campos com paginação, visite o tutorial:
Spring Data JPA Classificar/Ordenar por múltiplas colunas | Bota Primavera

# Página de dados do Spring

Vejamos o objeto Page .

Pageé uma subinterface Slicecom alguns métodos adicionais. Contém a quantidade total de elementos e o total de páginas de toda a lista.

```java
public interface Page<T> extends Slice<T> {
  static <T> Page<T> empty();
  static <T> Page<T> empty(Pageable pageable);
  long getTotalElements();
  int getTotalPages();
  <U> Page<U> map(Function<? super T,? extends U> converter);
}
```

Se o número de itens aumentar, o desempenho poderá ser afetado, é o momento em que você deve pensar no Slice .

Um Sliceobjeto conhece menos informações que um Page, por exemplo, se o próximo ou o anterior está disponível ou não, ou se esta fatia é a primeira/última. Você pode usá-lo quando não precisar do número total de itens e do total de páginas.

```java
public interface Slice<T> extends Streamable<T> {
  int getNumber();
  int getSize();
  int getNumberOfElements();
  List<T> getContent();
  boolean hasContent();
  Sort getSort();
  boolean isFirst();
  boolean isLast();
  boolean hasNext();
  boolean hasPrevious();
  ...
}
```

# Dados Spring pagináveis

Agora veremos o parâmetro Pageable nos métodos de repositório acima. A infraestrutura Spring Data reconhecerá este parâmetro automaticamente para aplicar paginação e classificação ao banco de dados.

A Pageableinterface contém as informações sobre a página solicitada, como tamanho e número da página.

```java
public interface Pageable {
  int getPageNumber();
  int getPageSize();
  long getOffset();
  Sort getSort();
  Pageable next();
  Pageable previousOrFirst();
  Pageable first();
  boolean hasPrevious();
  ...
}
```

Então quando queremos obter paginação (com ou sem filtro) nos resultados, apenas adicionamos Pageableà definição do método como parâmetro.

```java
Page<Tutorial> findAll(Pageable pageable);
Page<Tutorial> findByPublished(boolean published, Pageable pageable);
Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
```

É assim que criamos Pageableobjetos usando a classe PageRequest que implementa Pageablea interface:

Pageable paging = PageRequest.of(page, size);
page: índice de página baseado em zero, NÃO deve ser negativo.
size: número de itens em uma página a ser retornada, deve ser maior que 0.
