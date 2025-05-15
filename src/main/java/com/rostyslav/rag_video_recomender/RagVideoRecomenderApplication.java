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

  @Profile("test")
  @Bean
  public ApplicationRunner testPrompt(ChatClient chatClient) {
    return args -> log.info(chatClient.prompt("It is just Testing prompt! I test ollama spring ai integration!")
                                      .call().chatResponse().getResult().getOutput().getText());
  }

  @Bean
  @Profile("etl")
  public ApplicationRunner extractVideoDataToVectorStorage(VideoDetailsETL videoDetailsETL) {
    return args -> videoDetailsETL.extractVideoDataToVectorStorage();
  }

}
