package com.deyvisonborges.service.orders.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  // @Value("${rsa.public}")
  // private String publicKey;

  // @Bean
  // RSAPublicKey rsaPublicKey() throws Exception {
  //   String publicKeyPEM = publicKey.replaceAll("-----BEGIN PUBLIC KEY-----", "")
  //       .replaceAll("-----END PUBLIC KEY-----", "")
  //       .replaceAll("\\s", "");
  //   byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
  //   X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
  //   KeyFactory keyFactory = KeyFactory.getInstance("RSA");
  //   return (RSAPublicKey) keyFactory.generatePublic(spec);
  // }

  // @Bean
  // JwtDecoder jwtDecoder() throws Exception {
  //   return NimbusJwtDecoder.withPublicKey(rsaPublicKey()).build();
  // }
  
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((authz) -> authz
        .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
        .anyRequest().authenticated() 
      )
      .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
      return http.build();
    }
}
