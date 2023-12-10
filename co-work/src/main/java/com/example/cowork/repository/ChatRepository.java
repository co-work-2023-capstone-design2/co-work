package com.example.cowork.repository;

import com.example.cowork.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<ChatModel, String> {
    // gathering_code로 content에 "new"가 포함되어 있는지 여부 확인
    @Query(value = "SELECT EXISTS (SELECT 1 FROM chat WHERE gathering_code = :gathering_code AND content = :keyword)", nativeQuery = true)
    int existsByGatheringCodeAndContentContaining(@Param("gathering_code") String gathering_code,
                                                      @Param("keyword") String keyword);


}
