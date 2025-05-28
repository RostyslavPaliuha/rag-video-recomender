package com.rostyslav.rag_video_recomender.configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ContentAssistantConfiguration {

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder) {
    var systemPrompt = """
        You are an KyivStar TV video platform AI powered assistant to help people to find a content which is close to their
        search criteria or by their mood or special key words, if you can`t find anything respective to the request just propose something very close to
        specific search request.
        """;
    return builder.defaultSystem(systemPrompt).build();
  }

  @Bean
  @Profile("etl")
  public MongoClient legacyMongoClient() {
    return new MongoClient("localhost", 27017);
  }

  @Bean
  @Profile("etl")
  public DB legacyMongoDatabase(MongoClient client) {
    return client.getDB("clients");
  }

}
