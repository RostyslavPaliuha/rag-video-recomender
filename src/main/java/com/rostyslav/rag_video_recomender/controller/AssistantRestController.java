package com.rostyslav.rag_video_recomender.controller;

import com.rostyslav.rag_video_recomender.service.RAGSearhService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/assist")
@Tag(name = "Assistant API", description = "API for interacting with the AI assistant for video recommendations")
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
  @Operation(
      summary = "Simple chat with AI",
      description = "Sends a prompt directly to the AI model without RAG enhancement",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful response from AI",
              content = @Content(mediaType = "text/plain")
          )
      }
  )
  @GetMapping(value = "/", params = "prompt")
  public Mono<String> simpleChat(
      @Parameter(description = "The prompt to send to the AI model", required = true)
      @RequestParam("prompt") String prompt) {
    return Mono.just(prompt).subscribeOn(Schedulers.boundedElastic())
               .map(p -> chatClient.prompt(p).call().chatResponse().toString());
  }

  /**
   * RAG-enhanced chat endpoint that uses the RAGSearhService to enrich the prompt with relevant documents.
   */
  @Operation(
      summary = "RAG-enhanced chat with AI",
      description = "Sends a prompt to the AI model after enriching it with relevant documents using RAG",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful response from AI with RAG enhancement",
              content = @Content(mediaType = "text/plain")
          )
      }
  )
  @GetMapping(params = "prompt")
  public Mono<String> assistContent(
      @Parameter(description = "The prompt to be enhanced with RAG and sent to the AI model", required = true)
      @RequestParam("prompt") String prompt) {
    return Mono.just(prompt).subscribeOn(Schedulers.boundedElastic())
               .map(ragSearhService::searchAndEnrichPrompt);
  }

}
