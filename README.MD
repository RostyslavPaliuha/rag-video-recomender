# 🎬 RAG Video Recommender using Spring AI and Ollama

This project demonstrates a **Retrieval-Augmented Generation (RAG)** system built with **Spring Boot 3**, **Spring AI**, and **Ollama** running **locally**. It recommends relevant videos based on user prompts, leveraging semantic search through vector embeddings and local LLM inference.

---

## 🚀 Features

- 🧠 Local LLM inference using **Ollama** (`mistral`, `llama3`, etc.)
- 📦 Semantic vector search powered by **pgvector**
- 💬 Natural language interface (chat endpoint)
- 🔍 Retrieval-Augmented Generation (RAG) with contextual video matching
- ☁️ Runs fully offline – no cloud dependencies

---

## 🏗️ Tech Stack

- Java 24
- Spring Boot 3.x
- Spring AI 2024.0.x
- Ollama (local LLM runner)
- pgvector (PostgreSQL extension for vector storage)
- Docker (for running Postgres with pgvector)
- Maven

---

## 🛠️ Setup Instructions

### 1. 🧠 Install and Run Ollama

```bash
curl -fsSL https://ollama.com/install.sh | sh
ollama pull mistral
ollama serve
```

### 2. 📦 Run docker compose with pgvector 

```bash
docker compose up
```

### 3. 🚀 Run spring boot applicaiton in docker container or locally from console/idea 