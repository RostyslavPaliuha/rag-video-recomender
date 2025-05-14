package com.rostyslav.rag_video_recomender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
public class RagVideoRecomenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(RagVideoRecomenderApplication.class, args);
  }

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder) {
    return builder.build();
  }

  @Bean
  public ApplicationRunner testPrompt(ChatClient chatClient) {
    return args -> log.info( chatClient.prompt("It is just Testing prompt! I test ollama apring ai integration!").call().chatResponse().getResult().getOutput().getText());
  }

  @RestController
  @RequestMapping("/assist")
  public class AssistentRestController {

    private final ChatClient chatClient;

    public AssistentRestController(ChatClient chatClient) {
      this.chatClient = chatClient;
    }

    @PostMapping
    public String chat(@RequestBody String prompt) {
      ChatClient.ChatClientRequestSpec response = chatClient.prompt(prompt);
      return response.call().chatResponse().toString();
    }

  }

}
