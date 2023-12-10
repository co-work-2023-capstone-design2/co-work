package com.example.cowork.repository;

import com.example.cowork.model.GatheringModel;
import com.example.cowork.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
}
