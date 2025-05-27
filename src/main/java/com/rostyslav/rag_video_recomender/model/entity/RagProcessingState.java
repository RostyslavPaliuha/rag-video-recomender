package com.rostyslav.rag_video_recomender.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rag_processing_state")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
