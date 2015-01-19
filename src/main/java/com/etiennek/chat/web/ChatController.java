package com.etiennek.chat.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etiennek.chat.web.model.JoinChatRoomModel;

@Controller
@RequestMapping("/chat")
class ChatController {

  @RequestMapping
  String join(@ModelAttribute JoinChatRoomModel joinChatRoomModel) {
    return "chat/join-chat";
  }

  @RequestMapping(method = RequestMethod.POST)
  ModelAndView join(@Valid JoinChatRoomModel joinChatRoomModel, BindingResult result, RedirectAttributes redirect) {
    if (result.hasErrors()) {
      return new ModelAndView("chat/join-chat", "formErrors", result.getAllErrors());
    }
    return new ModelAndView("redirect:/chat/{joinChatRoomModel.roomName}", "joinChatRoomModel.roomName",
        joinChatRoomModel.getRoomName());
  }

  @RequestMapping("/{roomName}")
  ModelAndView room(@PathVariable String roomName, Principal principal) {
    return new ModelAndView("chat/chat");
  }

}
