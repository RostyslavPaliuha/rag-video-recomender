package com.rostyslav.rag_video_recomender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
@Service
@RequiredArgsConstructor
public class RAGSearhService {

  private final VectorStore vectorStore;

  private final ChatClient chatClient;

  public String searchAndEnrichPrompt(String query) {
    return chatClient.prompt().user(query)
                     .advisors(QuestionAnswerAdvisor.builder(vectorStore).build()).call().content();
  }

}
