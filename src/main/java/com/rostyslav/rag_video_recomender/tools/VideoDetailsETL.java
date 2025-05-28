package com.rostyslav.rag_video_recomender.tools;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Profile("etl")
@RequiredArgsConstructor
public class VideoDetailsETL {

  private final VectorStore vectorStore;

  private final DB mongoDb;

  public void extractVideoDataToVectorStorage() throws InterruptedException {
    DBCollection collection = mongoDb.getCollection("vod");
    DBCursor cursor = collection.find();
    List<Document> documents = new ArrayList<>();
    while (cursor.hasNext()) {
      DBObject doc = cursor.next();
      String assetId = doc.get("assetId").toString();
      String name = doc.get("name") != null ? doc.get("name").toString() : "";
      String plot = doc.get("plot") != null ? doc.get("plot").toString() : "";
      String genreList = doc.get("genreList") != null ? doc.get("genreList").toString() : "";
      String content = name + ". " + plot + "." + genreList;
      Document vectorDocument = new Document(content);
      Map<String, Object> metadata = vectorDocument.getMetadata();
      metadata.put("title", name);
      metadata.put("plot", plot);
      metadata.put("genreList", genreList);
      metadata.put("assetId", assetId);
      documents.add(vectorDocument);
      Thread.sleep(100);
      if (documents.size() == 50) {
        vectorStore.add(documents);
        documents.clear();
      }
    }
  }

}
