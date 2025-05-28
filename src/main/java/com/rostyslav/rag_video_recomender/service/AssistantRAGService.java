package com.rostyslav.rag_video_recomender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssistantRAGService {

  private final VectorStore vectorStore;

  private final ChatClient chatClient;

  public String assistWithRequestedContent(String prompt) {
    return chatClient.prompt()
                     .user(prompt)
                     .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                     .call()
                     .content();
  }

}
