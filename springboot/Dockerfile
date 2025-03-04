# 1. Gradle을 포함한 OpenJDK 이미지로 시작
FROM gradle:8.12.1-jdk17 AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 프로젝트 파일 복사
COPY . .

# 4. Gradle 빌드 실행 (배포용 JAR 파일을 생성)
RUN gradle build -x test

# 5. 실행 단계: JAR 파일을 실행할 OpenJDK 이미지를 사용
FROM openjdk:17-jdk-slim

# 6. 작업 디렉토리 설정
WORKDIR /app

# 7. 빌드된 JAR 파일 복사 (Gradle 빌드 결과물)
COPY --from=build /app/build/libs/*.jar app.jar

# 8. 실행 명령어
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
