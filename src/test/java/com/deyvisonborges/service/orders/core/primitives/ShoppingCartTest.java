package com.deyvisonborges.service.orders.core.primitives;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
  @Test
  void testCalculateTotalCost() {
    // Lista de produtos com seus preços em diferentes moedas
    List<Product> products = new ArrayList<>();
    products.add(new Product("Laptop", new Money(new BigDecimal("1000"), "USD")));
    products.add(new Product("Smartphone", new Money(new BigDecimal("800"), "EUR")));
    products.add(new Product("Headphones", new Money(new BigDecimal("1500"), "JPY")));

    // Mock de taxas de câmbio (para simplificar, consideramos que 1 USD = 1 EUR e 1
    // USD = 110 JPY)
    BigDecimal usdToEurRate = BigDecimal.ONE;
    BigDecimal usdToJpyRate = new BigDecimal("110");

    // Teste calcular custo total em USD
    Money totalCostUSD = calculateTotalCost(products, "USD");
    assertEquals(new Money(new BigDecimal("3300"), "USD"), totalCostUSD);

    // Teste calcular custo total em EUR
    Money totalCostEUR = calculateTotalCost(products, "EUR");
    assertEquals(new Money(new BigDecimal("2800"), "EUR"), totalCostEUR);

    // Teste calcular custo total em JPY
    Money totalCostJPY = calculateTotalCost(products, "JPY");
    assertEquals(new Money(new BigDecimal("363000"), "JPY"), totalCostJPY);
  }

  private static class Product {
    private String name;
    private Money price;

    public Product(String name, Money price) {
      this.name = name;
      this.price = price;
    }

    public String getName() {
      return name;
    }

    public Money getPrice() {
      return price;
    }
  }

  private static Money calculateTotalCost(List<Product> products, String targetCurrency) {
    Money totalCost = new Money(BigDecimal.ZERO, targetCurrency);
    for (Product product : products) {
      // Converter o preço do produto para a moeda alvo, se necessário
      Money priceInTargetCurrency = convertCurrency(product.getPrice(), targetCurrency);
      // Adicionar o preço convertido ao custo total
      totalCost = totalCost.add(priceInTargetCurrency);
    }
    return totalCost;
  }

  private static Money convertCurrency(Money amount, String targetCurrency) {
    // Implemente a lógica de conversão de moeda aqui
    // Exemplo simples: considera que a taxa de câmbio é 1 para a moeda de destino
    return amount;
  }
}
