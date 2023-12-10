/*
package com.example.cowork.chat.handler;

import com.example.cowork.chat.dto.ChatMessage;
import com.example.cowork.chat.dto.ChatRoom;
import com.example.cowork.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
//        String payload = message.getPayload();
//        log.info("{}", payload);
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//
//        ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
//        chatRoom.handlerActions(session, chatMessage, chatService);
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        if (chatMessage != null && chatMessage.getRoomId() != null) {
            ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
            if (chatRoom == null) {
                log.error("ChatRoom not found for id: {}", chatMessage.getRoomId());
                // 예외 처리 또는 다른 로직 추가
            } else {
                chatRoom.handlerActions(session, chatMessage, chatService);
            }
        } else {
            log.error("Invalid ChatMessage or RoomId is null");
            // 예외 처리 또는 다른 로직 추가
        }
    }


}
*/
