package com.example.cowork.etcData;

import com.example.cowork.payload.message.ChatMessage;
import com.example.cowork.service.ChatService;
import com.example.cowork.vo.GatheringVO;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Date;
import java.util.*;

@Data
public class ChatRoom {
    private final GatheringVO gatheringVO;

//    private Set<WebSocketSession> sessions = new HashSet<>();
    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Builder
    public ChatRoom(String gathering_code, String gathering_name, String gathering_owner, Date gathering_deleted_at){
            gatheringVO = new GatheringVO();
            gatheringVO.setGathering_code(gathering_code);
            gatheringVO.setGathering_name(gathering_name);
            gatheringVO.setGathering_owner(gathering_owner);
            gatheringVO.setGathering_deleted_at(gathering_deleted_at);
    }


    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
//            System.out.println("\n\n ENTER일 때 session: " + session);

            sessions.putIfAbsent(chatMessage.getGathering_code(), session);
//            sessions.put(chatMessage.getGathering_code(), session);
            chatMessage.setContent(chatService.getUserNameByUserEmail(chatMessage.getSender()) + "님이 입장했습니다.");
            System.out.println("입장 세션: " + session);
        }

        sendMessage(chatMessage, chatService);
    }

/*    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
//            System.out.println("\n\n ENTER일 때 session: " + session);

//            sessions.putIfAbsent(chatMessage.getGathering_code(), session);
            sessions.add(session);
            chatMessage.setContent(chatService.getUserNameByUserEmail(chatMessage.getSender()) + "님이 입장했습니다.");
            System.out.println("입장 세션: " + session);
        }else{
            // service에서 user_email과 일치하는 user_name보내기
            chatMessage.setSender_name(chatService.getUserNameByUserEmail(chatMessage.getSender()));
        }

        sendMessage(chatMessage, chatService);
    }*/

/*    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }*/

    private <T> void sendMessage(T message, ChatService chatService) {
        Collection<WebSocketSession> sessionValues = sessions.values();
        sessionValues.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }

}
