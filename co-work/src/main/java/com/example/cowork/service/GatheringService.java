package com.example.cowork.service;

import com.example.cowork.etcData.Date;
import com.example.cowork.model.GatheringModel;
import com.example.cowork.payload.response.GatheringInfoResponse;
import com.example.cowork.payload.response.MessageResponse;
import com.example.cowork.payload.response.RandomCodeResponse;
import com.example.cowork.payload.response.StateResponse;
import com.example.cowork.repository.GatheringRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Service
public class GatheringService {
    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private ChatService chatService;

    public ResponseEntity<?> createGatheringCode(){
        String randCode;

        do {
            randCode = RandomStringUtils.randomAlphanumeric(16);
        }while(gatheringRepository.existsById(randCode));

        return ResponseEntity.ok(new RandomCodeResponse(200, randCode));
    }

    public ResponseEntity<?> createGathering(String gathering_code,
                                             String gathering_exterior,
                                             String gathering_name,
                                             String gathering_owner,
                                             int gathering_floor,
                                             String gathering_explanation){
        GatheringModel newGathering = new GatheringModel();
        newGathering.setGathering_code(gathering_code);
        newGathering.setGathering_exterior(gathering_exterior);
        newGathering.setGathering_name(gathering_name);
        newGathering.setGathering_owner(gathering_owner);
        newGathering.setGathering_floor(gathering_floor);
        newGathering.setGathering_explanation(gathering_explanation);
        newGathering.setGathering_created_at(Date.getCurrentDate());

        // 디비에 모임 저장
        try {
            gatheringRepository.save(newGathering);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(400, "db 저장 error"));
        }

        // 채팅 만들기
        // 채팅방까지 잘 만들어지면 클라이언트로 200 보냄
        return chatService.createChatRoom(gathering_code);
        //return ResponseEntity.ok(new StateResponse(200));
    }


    public ResponseEntity<?> setLocation(
            String gathering_code,
            int x,
            int y
    ) {
        Optional<GatheringModel> gatheringModelOptional = gatheringRepository.findById(gathering_code);

        if (gatheringModelOptional.isPresent()){
            GatheringModel existingGatheringModel = gatheringModelOptional.get();

            // 필드 수정
            existingGatheringModel.setGathering_coord_x(x);
            existingGatheringModel.setGathering_coord_y(y);

            try {
                gatheringRepository.save(existingGatheringModel);
            } catch (DataIntegrityViolationException e){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(400, "db 저장 error"));
            }
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(400, "모임이 존재하지 않음"));
        }

        return ResponseEntity
                .ok(new StateResponse(200));
    }

    public ResponseEntity<?>getGatheringInfoByCode(String gathering_code){
        Optional<GatheringModel> optionalGatheringModel = gatheringRepository.findById(gathering_code);

        if (optionalGatheringModel.isPresent()) {
            GatheringModel existingGatheringModel = optionalGatheringModel.get();

            return ResponseEntity
                    .ok(new GatheringInfoResponse(
                            200,
                            existingGatheringModel.getGathering_code(),
                            existingGatheringModel.getGathering_exterior(),
                            existingGatheringModel.getGathering_name(),
                            existingGatheringModel.getGathering_owner(),
                            existingGatheringModel.getGathering_explanation()
                    ));
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(400, "모임이 존재하지 않음"));
    }
}
