# 1. Gradle 빌드 이미지 사용 (빌드 전용 단계)
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# 소스 복사 후 빌드
COPY . .
RUN gradle clean build -x test

# 2. 런타임 이미지 (슬림 JDK)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# 빌드 결과물 복사 (build/libs/*.jar)
COPY --from=builder /app/build/libs/*.jar app.jar

# 80번 포트 열기
EXPOSE 80

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]