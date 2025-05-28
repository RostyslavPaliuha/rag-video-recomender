package com.rostyslav.rag_video_recomender.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "content_vectors")
public class VideoVector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "text")
  private String content;  // name + plot + genres


  //@Type(type = "com.vladmihalcea.hibernate.type.array.FloatArrayType")
  @Column(columnDefinition = "vector(1536)")
  private float[] embedding;

  private String title;

  private String plot;

  private String genres;

  private String assetId;

}