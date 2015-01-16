package com.etiennek.chat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
class LoginController {

  @RequestMapping
  String login() {
    return "login";
  }

}
