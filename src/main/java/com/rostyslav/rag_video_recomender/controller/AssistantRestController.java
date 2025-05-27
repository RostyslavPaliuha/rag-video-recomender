package com.rostyslav.rag_video_recomender.controller;

import com.rostyslav.rag_video_recomender.service.RAGSearhService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/assist")
public class AssistantRestController {

  private final ChatClient chatClient;

  private final RAGSearhService ragSearhService;

  public AssistantRestController(ChatClient chatClient, RAGSearhService ragSearhService) {
    this.chatClient = chatClient;
    this.ragSearhService = ragSearhService;
  }

  /**
   * Simple chat endpoint that directly uses the ChatClient without RAG.
   */
  @PostMapping("/simple")
  public String simpleChat(@RequestBody String prompt) {
    ChatClient.ChatClientRequestSpec response = chatClient.prompt(prompt);
    return response.call().chatResponse().toString();
  }

  /**
   * RAG-enhanced chat endpoint that uses the RAGSearhService to enrich the prompt with relevant documents.
   */
  @PostMapping(params = "prompt")
  public Mono<String> ragChat(@RequestParam("prompt") String prompt) {
    return Mono.just(prompt)
        .subscribeOn(Schedulers.boundedElastic())
        .map(ragSearhService::searchAndEnrichPrompt);
  }

}
