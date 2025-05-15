package com.rostyslav.rag_video_recomender.repository;

import com.rostyslav.rag_video_recomender.model.entity.RagProcessingState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RagBatchStateRepository extends JpaRepository<RagProcessingState, Integer> {
}

