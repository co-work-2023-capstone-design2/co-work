package com.example.cowork.repository;

import com.example.cowork.model.GatheringModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollsRepository extends JpaRepository<GatheringModel, Integer> {
}
