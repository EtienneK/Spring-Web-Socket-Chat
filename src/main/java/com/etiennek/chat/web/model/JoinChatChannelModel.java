package com.etiennek.chat.web.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class JoinChatChannelModel {

  @NotEmpty(message = "Channel Name is required")
  @Pattern(regexp = "^\\w*$", message = "Channel Name may only consist of alphanumeric and underscore(_) characters")
  @Length(max = 30, message = "Channel Name must be a maximum of 30 characters in length")
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
