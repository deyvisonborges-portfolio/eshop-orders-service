package com.deyvisonborges.service.orders.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
  @GetMapping("/test")
  public String test() {
    return "Public route";
  }
}
