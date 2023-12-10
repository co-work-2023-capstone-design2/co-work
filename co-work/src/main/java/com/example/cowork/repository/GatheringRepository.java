package com.example.cowork.repository;

import com.example.cowork.model.GatheringModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GatheringRepository extends JpaRepository<GatheringModel, String> {
    // gathering_code로 gathering_owner 값 가져오기
    @Query(value = "SELECT gathering_owner FROM gathering WHERE gathering_code = :code", nativeQuery = true)
    String findGatheringOwnerByGatheringCode(@Param("code") String gathering_code);

}
