package com.etiennek.chat.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etiennek.chat.web.model.JoinChatChannelModel;

@Controller
@RequestMapping("/chat")
class ChatController {

  @Autowired
  private Validator validator;

  @RequestMapping
  String viewChannelJoinForm(@ModelAttribute JoinChatChannelModel joinChatChannelModel) {
    return "chat/join-channel-form";
  }

  @RequestMapping(method = RequestMethod.POST)
  ModelAndView actionChannelJoinForm(@Valid JoinChatChannelModel joinChatChannelModel, BindingResult result,
      RedirectAttributes redirect) {
    if (result.hasErrors()) {
      return new ModelAndView("chat/join-channel-form");
    }
    return new ModelAndView("redirect:/chat/{joinChatChannelModel.channelName}", "joinChatChannelModel.channelName",
        joinChatChannelModel.getChannelName());
  }

  @RequestMapping("/{channelName}")
  ModelAndView joinChannel(@PathVariable String channelName, @ModelAttribute JoinChatChannelModel joinChatChannelModel,
      BindingResult result, Principal principal) {
    joinChatChannelModel.setChannelName(channelName);
    validator.validate(joinChatChannelModel, result);
    if (result.hasErrors()) {
      return new ModelAndView("chat/join-channel-form");
    }
    return new ModelAndView("chat/chat");
  }

}
