package com.etiennek.chat.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {

  @RequestMapping("/room/{roomName}")
  ModelAndView index(@PathVariable String roomName, Principal principal) {
    return new ModelAndView("chat/room");
  }

}
