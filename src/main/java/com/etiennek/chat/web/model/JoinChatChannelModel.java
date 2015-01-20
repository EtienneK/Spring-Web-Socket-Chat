package com.etiennek.chat.web.model;

import org.hibernate.validator.constraints.NotEmpty;

public class JoinChatChannelModel {

  @NotEmpty(message = "Channel Name is required.")
  private String channelName;

  public JoinChatChannelModel() {
  }

  public JoinChatChannelModel(String channelName) {
    this.channelName = channelName;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

}
