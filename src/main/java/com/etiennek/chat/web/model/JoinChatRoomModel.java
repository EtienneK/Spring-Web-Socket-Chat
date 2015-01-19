package com.etiennek.chat.web.model;

import org.hibernate.validator.constraints.NotEmpty;

public class JoinChatRoomModel {
  @NotEmpty(message = "Room Name is required.")
  private String roomName;

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

}
