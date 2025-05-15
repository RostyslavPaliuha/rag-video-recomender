package com.rostyslav.rag_video_recomender.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "rag_processing_state")
public class RagProcessingState {

  @Id
  private Integer id;

  @Column(name = "current_batch")
  private int currentBatch;

  @Column(name = "batch_size")
  private int batchSize;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

}


