CREATE TABLE content_vectors
(
    id         TEXT PRIMARY KEY,
    content    TEXT NOT NULL, -- Combined field (name + plot + genreList)
    metadata   JSONB,
    embedding  VECTOR(768),  -- Vector representation
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create index for similarity search
CREATE INDEX ON content_vectors USING hnsw (embedding vector_cosine_ops);
