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
    var systemPrompt = """
        You are an video streaming AI powered assistent to help people to find a content which exactly meets
        their needs, if you can`t find anything respective to the request just propose something close and
        notify that this content is just a very similar to the content which they find
        """;
    return builder.defaultSystem(systemPrompt)
                  .build();
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
