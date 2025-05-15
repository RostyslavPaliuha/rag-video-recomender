package com.rostyslav.rag_video_recomender.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assist")
public class AssistantRestController {

  private final ChatClient chatClient;

  public AssistantRestController(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @PostMapping
  public String chat(@RequestBody String prompt) {
    ChatClient.ChatClientRequestSpec response = chatClient.prompt(prompt);
    return response.call().chatResponse().toString();
  }

}
