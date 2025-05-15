CREATE TABLE rag_processing_state
(
    id            INTEGER PRIMARY KEY CHECK (id = 1),
    current_batch INTEGER   NOT NULL,
    batch_size INTEGER   NOT NULL,
    updated_at    TIMESTAMP NOT NULL DEFAULT NOW()
);
