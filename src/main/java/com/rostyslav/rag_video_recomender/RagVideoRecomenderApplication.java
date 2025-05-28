package com.rostyslav.rag_video_recomender;

import com.rostyslav.rag_video_recomender.tools.VideoDetailsETL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Slf4j
@SpringBootApplication
public class RagVideoRecomenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(RagVideoRecomenderApplication.class, args);
  }

  @Bean
  @Profile("etl")
  public ApplicationRunner extractVideoDataToVectorStorage(VideoDetailsETL videoDetailsETL) {
    return args -> videoDetailsETL.extractVideoDataToVectorStorage();
  }

}
