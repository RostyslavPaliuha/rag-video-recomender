package com.rostyslav.rag_video_recomender.repository;

import com.rostyslav.rag_video_recomender.model.entity.RagProcessingState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:ragBatchStateRepository.properties")
class RagBatchStateRepositoryTest {

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine").withDatabaseName("testdb")
                                                                                                     .withUsername("testuser")
                                                                                                     .withPassword("testpass");

  @Autowired
  RagBatchStateRepository ragBatchStateRepository;

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
  }

  @Test
  void shouldSaveRagProcessingState() {
    // Given
    RagProcessingState state = RagProcessingState.builder().id(1).currentBatch(5).batchSize(10)
                                                 .updatedAt(LocalDateTime.now()).build();
    // When
    RagProcessingState savedState = ragBatchStateRepository.save(state);
    // Then
    assertThat(savedState).isNotNull();
    assertThat(savedState.getId()).isEqualTo(1);
    assertThat(savedState.getCurrentBatch()).isEqualTo(5);
    assertThat(savedState.getBatchSize()).isEqualTo(10);
    assertThat(savedState.getUpdatedAt()).isNotNull();
  }

  @Test
  void shouldGetRagProcessingState() {
    // Given
    RagProcessingState state = RagProcessingState.builder().id(1).currentBatch(5).batchSize(10)
                                                 .updatedAt(LocalDateTime.now()).build();
    ragBatchStateRepository.save(state);
    // When
    RagProcessingState retrievedState = ragBatchStateRepository.findById(1).orElse(null);
    // Then
    assertThat(retrievedState).isNotNull();
    assertThat(retrievedState.getId()).isEqualTo(1);
    assertThat(retrievedState.getCurrentBatch()).isEqualTo(5);
    assertThat(retrievedState.getBatchSize()).isEqualTo(10);
    assertThat(retrievedState.getUpdatedAt()).isNotNull();
  }

}
