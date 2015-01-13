package com.etiennek.chat.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {

  @RequestMapping("/room")
  ModelAndView index(Principal principal) {
    return new ModelAndView("chat/room");
  }

}
