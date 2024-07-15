package com.deyvisonborges.service.orders.app.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
  @Value("${pwt.public.key}")
  private RSAPublicKey publicKey;

  @Value("${pwt.private.key}")
  private RSAPrivateKey privateKey;

  @SuppressWarnings("unused")
  private SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .authorizeHttpRequests(
      // requer autenticacao para qualquer requisicao
      authorize -> authorize.anyRequest().authenticated())
      // disable localmente temporariamente
      .csrf(csrf -> csrf.disable())
      // usarei o jwt no projeto e ativei as configuracoes padroes
      .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
      // define que nao preciso guardar nada em sessao
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      return http.build();
  }
}