package com.rostyslav.rag_video_recomender.tools;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VideoDetailsETL {

  @Autowired
  VectorStore vectorStore;

  @Autowired
  private DB mongoDb;

  public void extractVideoDataToVectorStorage() throws InterruptedException {
    DBCollection collection = mongoDb.getCollection("vod");
    DBCursor cursor = collection.find();
    List<Document> documents = new ArrayList<>();

    while (cursor.hasNext()) {
      DBObject doc = cursor.next();
      String name = doc.get("name") != null ? doc.get("name").toString() : "";
      String plot = doc.get("plot") != null ? doc.get("plot").toString() : "";
      String genreList = doc.get("genreList") != null ? doc.get("genreList").toString() : "";
      String content = name + ". " + plot + "." + genreList;
      Document vectorDocument = new Document(content);
      Map<String, Object> metadata = vectorDocument.getMetadata();
      metadata.put("title", name);
      metadata.put("plot", plot);
      metadata.put("genreList", genreList);
      documents.add(vectorDocument);
      Thread.sleep(1000);
      if (documents.size() == 10) {
        vectorStore.add(documents);
        documents.clear();
      }
    }
  }

}
