package com.etiennek.chat.web;

import java.security.Principal;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etiennek.chat.web.model.JoinChatChannelModel;
import com.etiennek.ws.topic.TopicManager;

@Controller
@RequestMapping("/chat")
class ChatController {

  @Autowired
  private Validator validator;

  @Autowired
  private TopicManager topicManager;

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

  @RequestMapping("/{channelName}/join")
  @ResponseBody
  boolean joinChannel(Principal principal, @PathVariable String channelName) {
    topicManager.addSubscriber(channelName, principal.getName());
    return true;
  }

  @RequestMapping("/{channelName}/users")
  @ResponseBody
  Set<String> listUsers(@PathVariable String channelName) {
    return topicManager.getSubscribers(channelName);
  }

}
