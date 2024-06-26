# Anotações Spring Data MongoDB mais usadas

### @Document

A anotação @Document representa uma coleção do MongoDB. É semelhante à anotação Entity da API de persistência do Java. Devemos colocar esta anotação no nível da classe. Se o nome da coleção for diferente do nome da classe, podemos especificar o nome usando a propriedade collection ou value.

### @Field

A anotação @Field é usada para representar um campo em uma coleção. É semelhante à anotação Column na API de persistência do Java, mas não possui tantas propriedades como java.persistence.Column.

**Propriedades do @Field:**

- `name` → para especificar o nome do campo armazenado no MongoDB. É útil quando o nome do campo na classe é diferente do nome do campo na coleção.
- `order` → para especificar a ordem do campo em relação a outros campos. Um número maior significa que será armazenado por último.
- `targetType` → usado para especificar o tipo de valor do campo anotado (IMPLICIT, DOUBLE, STRING, ARRAY, BINARY, OBJECT_ID, etc.)

### @Id

A anotação @Id não pertence ao módulo spring-data-mongodb; ela é usada tanto para spring-data-jpa quanto para spring-data-mongodb. No contexto do MongoDB, essa anotação indica que um campo deve ser usado como identificador para cada documento.

### @DBRef

Imagine que temos um documento chamado Car e queremos incluir informações sobre o fabricante do carro. Para esses casos, devemos definir uma nova classe de documento chamada Manufacturer e usá-la como um campo de Car com a anotação @DBRef.

### @Transient

**As anotações @Indexed, @TextIndexed, @CompoundIndex**
Essas anotações ajudam a especificar que um campo deve fazer parte do índice no MongoDB.

- A anotação @Indexed é usada para marcar um campo a ser indexado e a @TextIndexed é mais específica para um campo de texto.
- A anotação @CompoundIndex é usada para combinar múltiplos campos como um índice.

A anotação @Transient indica que um campo não será persistido ao mapear dados do MongoDB. O uso desta anotação é semelhante à palavra-chave transient no Java.

## Criteria

#### andOperator

- `Como Funciona o andOperator`
  O andOperator permite agrupar vários objetos Criteria de forma que todos os critérios especificados devem ser atendidos. É equivalente à cláusula AND em uma consulta SQL.

- `Exemplo de Uso`
  Vamos considerar um exemplo onde você deseja consultar documentos em uma coleção onde o status é "SHIPPED" e a data do pedido está entre duas datas específicas.
