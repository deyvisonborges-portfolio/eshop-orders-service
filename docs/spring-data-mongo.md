# Anotações Spring Data MongoDB mais usadas

### @Document
Esta anotação representa uma coleção MongoDB. É semelhante à anotação de entidade da API de persistência Java. Devemos 
colocar esta anotação no nível da classe. Se o nome da coleção for diferente do nome da classe, podemos especificar o 
nome usando a propriedade da coleção ou a propriedade do valor.

### @Field
A anotação do campo
A anotação Field é usada para representar um campo em uma coleção. É semelhante à anotação de coluna na API de persistência java, mas não possui muitas propriedades como java.persistence.Column.

**Propriedades do campo:**
- **name** → para especificar o nome do campo armazenado no MongoDB. É útil quando o campo da classe é diferente do campo da coleção.
- **order** → para especificar a ordem do campo atual com outros campos. O número grande significa armazenar no último.
- **targetType** → usado para especificar o tipo de valor do campo anotado (IMPLICIT, DOUBLE, STRING, ARRAY, BINARY, OBJECT_ID, ..)

### @Id
A anotação de ID não pertence ao módulo spring-data-mongodb , ela pertence aos dados do spring para amplamente 
utilizados tanto para spring-data-jpa quanto para spring-data-mongodb . No termo MongoDB, esta anotação indica que um 
campo deve ser usado como identificador para cada documento.