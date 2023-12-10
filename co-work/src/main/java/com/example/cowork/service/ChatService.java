package com.example.cowork.service;

import com.example.cowork.etcData.ChatRoom;
import com.example.cowork.etcData.Date;
import com.example.cowork.model.ChatModel;
import com.example.cowork.model.GatheringModel;
import com.example.cowork.payload.message.ChatMessage;
import com.example.cowork.payload.response.MessageResponse;
import com.example.cowork.payload.response.StateResponse;
import com.example.cowork.repository.ChatRepository;
import com.example.cowork.repository.GatheringRepository;
import com.example.cowork.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper;
    private static Map<String, ChatRoom> chatRooms;

    @PostConstruct
    public void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllChat(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findGatheringByCode(String gathering_code){
        System.out.println("return chatRooms.get(gathering_code);");
        return chatRooms.get(gathering_code);
    }

    public boolean isChatRoomExist(String gathering_code){
        return chatRooms.containsKey(gathering_code);
    }

    public ResponseEntity<?> createChatRoom(String gathering_code){
        if (isChatRoomExist(gathering_code)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(201, "이미 채팅방이 존재합니다."));
        }else{
            addGatheringList(gathering_code);

            return ResponseEntity
                    .ok(new StateResponse(200));
        }
    }

    private void addGatheringList(String gathering_code){
        GatheringModel gathering = gatheringRepository.getById(gathering_code);
        System.out.println("모임 이름 : "+ gathering);
        ChatRoom chatRoom = ChatRoom.builder()
                .gathering_code(gathering_code)
                .gathering_name(gathering.getGathering_name())
                .gathering_owner(gathering.getGathering_owner())
                .gathering_deleted_at(gathering.getGathering_deleted_at())
                .build();
        chatRooms.put(gathering_code, chatRoom);

        if (!gatheringRepository.existsById(gathering_code) || chatRepository.existsByGatheringCodeAndContentContaining(gathering_code, "NEW")==0) {
            ChatModel newChat = new ChatModel();
            newChat.setGathering_code(gathering_code);
            newChat.setContent("NEW");
            newChat.setSender(gatheringRepository.findGatheringOwnerByGatheringCode(gathering_code));
            newChat.setSendTime(Date.getCurrentDateTime());

            chatRepository.save(newChat);
        }else{
            log.info("db에 채팅 기록이 존재하지만 chatRooms 리스트에 없어서 추가함");
        }
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        if (message instanceof ChatMessage){
            ((ChatMessage) message).setSendTime(Date.getCurrentDateTime());

            ChatMessage chatMessage = (ChatMessage) message;
            System.out.println(chatMessage);

            ChatModel newChat = new ChatModel();
            newChat.setGathering_code(chatMessage.getGathering_code());
            newChat.setContent(chatMessage.getContent());
            newChat.setSender(chatMessage.getSender());
            newChat.setSendTime(chatMessage.getSendTime());
            newChat.setChat_session(session.getId());

            chatRepository.save(newChat);

            // service에서 user_email과 일치하는 user_name보내기
            ((ChatMessage) message).setSender_name(getUserNameByUserEmail(chatMessage.getSender()));

            /*System.out.println("if (!isChatRoomExist(chatMessage.getGathering_code())){")
            if (!isChatRoomExist(chatMessage.getGathering_code())){
                System.out.println("addGatheringList(chatMessage.getGathering_code());");
                addGatheringList(chatMessage.getGathering_code());
            }*/
        }


        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }

    public String getUserNameByUserEmail(String user_email){
        return userRepository.findUserNameByUserEmail(user_email);
    }
}
