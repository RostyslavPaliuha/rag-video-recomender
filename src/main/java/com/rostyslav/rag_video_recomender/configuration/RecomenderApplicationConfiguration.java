package com.rostyslav.rag_video_recomender.configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecomenderApplicationConfiguration {

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder) {
    return builder.build();
  }

  @Bean
  public MongoClient legacyMongoClient() {
    return new MongoClient("localhost", 27017);
  }

  @Bean
  public DB legacyMongoDatabase(MongoClient client) {
    return client.getDB("clients");
  }

}
