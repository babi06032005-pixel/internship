# BookMate — Complete Run & Deployment Guide
## Peer-to-Peer Book Marketplace with AI Insights

---

## 📁 Project Structure

```
bookmate-final/
├── backend/                        ← Spring Boot (Java 17)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/bookmate/
│       │   ├── BookMateApplication.java
│       │   ├── entity/             User, Book, BookMedia, ChatMessage, BookInsight, PriceComparison
│       │   ├── dto/                Dto.java (all request/response objects)
│       │   ├── repository/         6 JPA repositories
│       │   ├── service/            AuthService, BookService, MediaService, ChatService,
│       │   │                       BookInsightService, ChatbotService
│       │   ├── controller/         AuthController, BookController, MediaController,
│       │   │                       ChatController, ApiControllers (Insight+Price+Chatbot)
│       │   ├── config/             JwtUtil, SecurityConfig, WebSocketConfig, WebMvcConfig
│       │   └── exception/          GlobalExceptionHandler
│       └── resources/
│           └── application.properties
└── frontend/
    └── index.html                  ← Complete SPA (single file, no build needed)
```

---

## ✅ Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 17+ | https://adoptium.net |
| Maven | 3.8+ | https://maven.apache.org |
| MySQL | 8.0+ (optional, H2 works for dev) | https://mysql.com |
| Node.js | Not needed | — |

---

## 🚀 QUICKSTART (5 minutes, H2 in-memory DB)

### Step 1 — Clone / Unzip the project
```bash
cd ~/Desktop
unzip bookmate.zip    # or wherever you saved it
cd bookmate-final
```

### Step 2 — (Optional) Set your API keys
Open `backend/src/main/resources/application.properties` and set:
```properties
bookmate.openai.api-key=sk-YOUR_OPENAI_KEY_HERE
bookmate.google.books.api-key=YOUR_GOOGLE_BOOKS_KEY_HERE
```
> Skip this step to run without AI features (demo mode still works).

### Step 3 — Build & run the backend
```bash
cd backend
mvn clean package -DskipTests
java -jar target/bookmate-backend-1.0.0.jar
```
Backend starts at → **http://localhost:8080**

### Step 4 — Open the frontend
Simply open the frontend file in your browser:
```
frontend/index.html   ← double-click this file
```
Or serve it locally:
```bash
cd ../frontend
python3 -m http.server 3000
# then open http://localhost:3000
```

---

## 🐬 Using MySQL (Production)

### Create database
```sql
CREATE DATABASE bookmate_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'bookmate'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON bookmate_db.* TO 'bookmate'@'localhost';
FLUSH PRIVILEGES;
```

### Switch to MySQL in application.properties
Comment out the H2 block and uncomment MySQL:
```properties
# Comment these 5 H2 lines:
# spring.datasource.url=jdbc:h2:mem:...
# spring.datasource.driver-class-name=org.h2.Driver
# spring.datasource.username=sa
# spring.datasource.password=
# spring.jpa.database-platform=...H2Dialect

# Uncomment these MySQL lines:
spring.datasource.url=jdbc:mysql://localhost:3306/bookmate_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=bookmate
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# For production, change to:
spring.jpa.hibernate.ddl-auto=update
```

---

## 🌍 REST API Reference

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/auth/register | ❌ | Register new user |
| POST | /api/auth/login | ❌ | Login, returns JWT |
| GET | /api/auth/profile/{id} | ✅ | Get user profile |
| GET | /api/books | ❌ | List books (paginated, search, filter) |
| GET | /api/books/{id} | ❌ | Get single book |
| POST | /api/books | ✅ | Create listing |
| PUT | /api/books/{id} | ✅ | Update listing |
| DELETE | /api/books/{id} | ✅ | Delete listing |
| GET | /api/books/my | ✅ | My listings |
| POST | /api/media/upload/{bookId} | ✅ | Upload image/video |
| POST | /api/chat/send | ✅ | Send message |
| GET | /api/chat/conversation | ✅ | Get conversation |
| GET | /api/insight/{bookId} | ❌ | Get AI insight |
| GET | /api/price/{bookId} | ❌ | Get price dashboard |
| POST | /api/chatbot | ❌ | AI chatbot query |
| WS | /ws (STOMP) | ✅ | Real-time chat |

---

## 🔧 Environment Variables

```bash
# Linux/Mac
export OPENAI_API_KEY=sk-...
export GOOGLE_BOOKS_API_KEY=AIza...

# Windows
set OPENAI_API_KEY=sk-...
set GOOGLE_BOOKS_API_KEY=AIza...
```

Or pass directly:
```bash
java -DOPENAI_API_KEY=sk-... -jar target/bookmate-backend-1.0.0.jar
```

---

## 📦 Building for Production

### Backend JAR
```bash
cd backend
mvn clean package -DskipTests
# Produces: target/bookmate-backend-1.0.0.jar
```

### Run with production profile
```bash
java -Xms256m -Xmx512m \
  -DOPENAI_API_KEY=sk-... \
  -DGOOGLE_BOOKS_API_KEY=AIza... \
  -jar target/bookmate-backend-1.0.0.jar
```

### Deploy frontend
The `frontend/index.html` is a single file — deploy it anywhere:
- **Netlify**: Drag & drop `frontend/` folder
- **GitHub Pages**: Push to repo
- **Nginx**: Copy to `/var/www/html/`
- **Spring Boot static**: Copy to `backend/src/main/resources/static/` and serve from same server

### Update API URL in frontend
Open `frontend/index.html`, find line:
```javascript
const API = 'http://localhost:8080/api';
```
Change to your deployed backend URL:
```javascript
const API = 'https://your-server.com/api';
```

---

## 🐳 Docker Deployment

### Dockerfile (backend)
```dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/bookmate-backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### docker-compose.yml
```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpass
      MYSQL_DATABASE: bookmate_db
      MYSQL_USER: bookmate
      MYSQL_PASSWORD: bookpassword
    ports: ["3306:3306"]
    volumes: [db_data:/var/lib/mysql]

  backend:
    build: ./backend
    ports: ["8080:8080"]
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/bookmate_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_USERNAME: bookmate
      SPRING_DATASOURCE_PASSWORD: bookpassword
      OPENAI_API_KEY: ${OPENAI_API_KEY}
      GOOGLE_BOOKS_API_KEY: ${GOOGLE_BOOKS_API_KEY}
    depends_on: [db]

volumes:
  db_data:
```

```bash
# Build and run
mvn clean package -DskipTests   # inside backend/
docker-compose up --build
```

---

## 🔑 Getting API Keys

### OpenAI (for AI Insights + Chatbot)
1. Go to https://platform.openai.com/api-keys
2. Create a new key
3. Set `OPENAI_API_KEY=sk-...`
4. Note: Uses `gpt-3.5-turbo` (~$0.002/1K tokens, very cheap)

### Google Books API (for ratings/metadata)
1. Go to https://console.cloud.google.com
2. Enable "Books API"
3. Create API Key
4. Set `GOOGLE_BOOKS_API_KEY=AIza...`
5. Free tier: 1000 requests/day

---

## 🎯 Features Summary

| Feature | Status |
|---------|--------|
| User Registration & Login (JWT) | ✅ Full |
| Book Listing (CRUD) | ✅ Full |
| Image/Video Upload | ✅ Full |
| Real-time Chat (WebSocket/STOMP) | ✅ Full |
| AI Book Insights (OpenAI GPT) | ✅ Needs API key |
| Price Intelligence Dashboard | ✅ Simulated (real APIs pluggable) |
| AI Chatbot Widget | ✅ Needs API key |
| Search & Filter & Pagination | ✅ Full |
| Demo Mode (no backend) | ✅ Works offline |
| H2 (dev) / MySQL (prod) | ✅ Both supported |

---

## ❓ Troubleshooting

**Port 8080 in use:**
```bash
java -Dserver.port=9090 -jar target/bookmate-backend-1.0.0.jar
```

**CORS errors (frontend on different port):**
Add your frontend URL to `application.properties`:
```properties
bookmate.cors.allowed-origins=http://localhost:3000,http://localhost:8080
```

**Maven not found:**
```bash
# Ubuntu/Debian
sudo apt install maven

# macOS
brew install maven
```

**H2 console (inspect DB during dev):**
Open http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:bookmate_db`
- Username: `sa` | Password: (blank)

---

*BookMate © 2024 — Java 17 + Spring Boot 3.2 + H2/MySQL + WebSocket + OpenAI*
