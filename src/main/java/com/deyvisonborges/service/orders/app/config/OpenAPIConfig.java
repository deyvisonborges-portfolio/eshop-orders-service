package com.deyvisonborges.service.orders.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Arrays;

@Configuration
public class OpenAPIConfig {
  @Value("${openapi.dev-url}")
  private String devUrl;

  @Value("${openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI openAPI() {
    final var devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.description("Server running in " + devUrl + " environment");

    final var prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.description("Server running in " + prodUrl + " environment");

    Contact contact = new Contact();
    contact.setEmail("https://github.com.deyvisonborges");
    contact.setName("Deyvison Borges");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
      .title("eShop Payment API")
      .version("1.0")
      .contact(contact)
      .description("This API exposes endpoints to manage payments.")
      .license(mitLicense);

    return new OpenAPI().info(info).servers(Arrays.asList(devServer, prodServer));
  }
}