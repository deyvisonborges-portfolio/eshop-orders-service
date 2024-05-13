package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.repository.OrderRepositoryGateway;

import jakarta.transaction.Transactional;

@Repository
public class OrderRepository implements OrderRepositoryGateway {
  private final OrderJPARepository jpaRepository;

  public OrderRepository(final OrderJPARepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  @Transactional
  public void save(Order order) {
    try {
      final var orderToSave = OrderJPAEntity.toJPAEntity(order);
      // Salvar o pedido e obter o pedido salvo
      final var savedOrder = this.jpaRepository.save(orderToSave);

      // Atualizar cada item de pedido com o pedido salvo
      for (OrderItemJPAEntity item : savedOrder.getItems()) {
        item.setOrder(savedOrder);
      }

      // // Atribuir manualmente um ID para cada pagamento antes de salvar
      // for (OrderPaymentJPAEntity payment : savedOrder.getPayments()) {
      //   if(payment.getId() == null) {
      //     payment.setId(UUID.randomUUID().toString()); // Atribuir um novo ID apenas se o ID estiver nulo
      //   }
      //   payment.setOrder(savedOrder);
      // }
      
      // Salvar o pedido novamente com os itens e pagamentos atualizados
      this.jpaRepository.save(savedOrder);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save Order on JPA Repository: " + e.getMessage());
    }
  }

  @Override
  public void saveAll(List<Order> orders) {
    try {
      List<OrderJPAEntity> entities = orders.stream()
      .map(OrderJPAEntity::toJPAEntity)
      .collect(Collectors.toList());
    this.jpaRepository.saveAll(entities);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save all Orders on JPA Repository");
    }
  }

  @Override
  public List<Order> findAll() {
    try {
      return this.jpaRepository.findAll()
      .stream()
      .map(OrderJPAEntity::toAggregate)
      .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Fail to return all orders from JPA Repository");
    }
  }

  @Override
  public void deleteById(String id) {
    try {
      this.jpaRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in JPA Repository");
    }
  }

  @Transactional
  /**************************************************
   * Iniciar uma transação antes de acessar a coleção: 
   * Isso garante que a sessão do Hibernate 
   * esteja aberta durante o acesso à coleção.
   * ************************************************/
  public Optional<Order> findById(String id) {
    final var order = this.jpaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Not found order with id " + id));
    return Optional.of(OrderJPAEntity.toAggregate(order));
  }
}