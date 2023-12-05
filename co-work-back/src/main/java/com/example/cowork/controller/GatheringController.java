package com.example.cowork.controller;

import com.example.cowork.model.GatheringModel;
import com.example.cowork.payload.request.CreateGatheringRequest;
import com.example.cowork.payload.request.SetLocationRequest;
import com.example.cowork.repository.GatheringRepository;
import com.example.cowork.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    @GetMapping("/createGatheringCode")
    public ResponseEntity<?> createGatheringCode(){
        return gatheringService.createGatheringCode();
    }

    @PostMapping("/createGathering")
    public ResponseEntity<?> createGathering(@RequestBody CreateGatheringRequest createGatheringRequest){
        return gatheringService.createGathering(
                createGatheringRequest.getGathering_code(),
                createGatheringRequest.getGathering_exterior(),
                createGatheringRequest.getGathering_name(),
                createGatheringRequest.getGathering_owner(),
                createGatheringRequest.getGathering_floor(),
                createGatheringRequest.getGathering_explanation());
    }

    @PostMapping("/setLocation")
    public ResponseEntity<?> setLocation(@RequestBody SetLocationRequest setLocationRequest){
        return gatheringService.setLocation(setLocationRequest.getGathering_code(), setLocationRequest.getX(), setLocationRequest.getY());
    }

    @GetMapping("/getGatheringInfoByCode/{gathering_code}")
    public ResponseEntity<?> getGatheringInfoByCode(@PathVariable("gathering_code") String gathering_code){
        return gatheringService.getGatheringInfoByCode(gathering_code);
    }

}
