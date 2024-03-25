package com.dxhh.elearning.controllers.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class RoomWSController {

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public RoomWSController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

//    @MessageMapping("/create-room")
//    public void createRoom(@Payload Room room, SimpMessageHeaderAccessor headerAccessor) {
//        String roomId = roomManager.createRoom(room.getName());
//        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");
//        roomManager.addUserToRoom(roomId, username);
//        messagingTemplate.convertAndSendToUser(username, "/room/created", new RoomCreatedMessage(roomId));
//    }
//
//    @MessageMapping("/join-room")
//    public void joinRoom(@Payload JoinRoomMessage message, SimpMessageHeaderAccessor headerAccessor) {
//        String roomId = message.getRoomId();
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        roomManager.addUserToRoom(roomId, username);
//        Room room = roomManager.getRoom(roomId);
//        messagingTemplate.convertAndSend("/room/" + roomId, new UserJoinedMessage(username));
//    }
//
//    @MessageMapping("/leave-room")
//    public void leaveRoom(@Payload LeaveRoomMessage message, SimpMessageHeaderAccessor headerAccessor) {
//        String roomId = message.getRoomId();
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        roomManager.removeUserFromRoom(roomId, username);
//        Room room = roomManager.getRoom(roomId);
//        messagingTemplate.convertAndSend("/room/" + roomId, new UserLeftMessage(username));
//    }
//
//    @MessageMapping("/send-message")
//    public void sendMessage(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
//        String roomId = message.getRoomId();
//        String sender = (String) headerAccessor.getSessionAttributes().get("username");
//        Room room = roomManager.getRoom(roomId);
//        if (room != null) {
//            ChatMessage chatMessage = new ChatMessage(sender, message.getContent());
//            messagingTemplate.convertAndSend("/room/" + roomId, chatMessage);
//        }
//    }
//
//    @MessageMapping("/kick-user")
//    public void kickUser(@Payload KickUserMessage message, SimpMessageHeaderAccessor headerAccessor) {
//        String roomId = message.getRoomId();
//        String username = message.getUsername();
//        String kickedBy = (String) headerAccessor.getSessionAttributes().get("username");
//        roomManager.removeUserFromRoom(roomId, username);
//        Room room = roomManager.getRoom(roomId);
//        messagingTemplate.convertAndSend("/topic/" + roomId, new UserKickedMessage(username, kickedBy));
//    }
}
