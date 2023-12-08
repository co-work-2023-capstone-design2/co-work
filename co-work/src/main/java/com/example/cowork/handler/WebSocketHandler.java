package com.example.cowork.handler;

import com.example.cowork.etcData.ChatRoom;
import com.example.cowork.payload.message.ChatMessage;
import com.example.cowork.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("session: " + session);
        String payload = message.getPayload();
        log.info("{}", payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        if (chatMessage != null && chatMessage.getGathering_code() != null) {
            ChatRoom chatRoom = chatService.findGatheringByCode(chatMessage.getGathering_code());
            if (chatRoom == null) {
                log.error("ChatRoom not found for id: {}", chatMessage.getGathering_code());
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


