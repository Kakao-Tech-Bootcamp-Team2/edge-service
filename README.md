# Zipbob Edge Service

## 개요 🚀

Edge Service는 **집밥 요리사 프로젝트**의 핵심 서비스로, API Gateway 역할을 수행합니다. 모든 클라이언트 요청이 Edge Service를 통해 처리되며, **사용자 인증 및 인가**를 수행한 후 내부 마이크로서비스로 전달됩니다.

<br />

## 주요 기능 🔥

- **API Gateway 역할 수행**: 모든 요청을 수집하고 적절한 마이크로서비스로 라우팅
- **사용자 인증 및 인가**: JWT 및 OAuth2.0을 활용한 인증 및 권한 검증
- **보안 강화**: Spring Security를 활용한 보안 정책 적용
- **Rate Limiting**: Redis 기반의 속도 제한 적용 (Spring Cloud Gateway Filter 활용)

<br />

## 아키텍처 개요 🏗️

Edge Service는 **API Gateway**로 동작하며, 다른 마이크로서비스와 연동됩니다. 주요 흐름은 다음과 같습니다:

1. 클라이언트의 모든 요청은 Edge Service를 통해 전달됩니다.
2. JWT 또는 OAuth2.0을 사용하여 사용자 인증을 수행합니다.
3. 인증된 요청만이 Gateway Filter를 거쳐 적절한 마이크로서비스로 전달됩니다.
4. 필요 시 **Rate Limiting**을 적용하여 과도한 요청을 방지합니다.

```
(Client) -> [Edge Service] -> (Backend Services)
```

<br />

## 기술 스택 🛠️

- **Java 17**
- **Spring Framework**
- **Spring Cloud Gateway**
- **Spring Security 6**
- **MariaDB**
- **Redis**
- **RabbitMQ**

<br />

## 설치 및 실행 방법 💻

### 1. 로컬 실행

```sh
./gradlew build && ./gradlew bootRun
```

### 2. Docker 이미지 빌드

```sh
./gradlew bootBuildImage
```

### 3. Docker 실행

Docker 실행 방법 및 관련 설정에 대한 자세한 내용은 아래 저장소의 README를 참고해 주세요.

📌 저장소 링크: [Zipbob Deployment Repository](https://github.com/Kakao-Tech-Bootcamp-Team2/zipbob-deployment)

<br />

## 데이터베이스 전략 📊

Edge Service는 **마스터-슬레이브 전략**을 활용하여 데이터베이스 부하를 분산하고 가용성을 높입니다. 🏆

- **마스터 노드**는 모든 **쓰기 작업**을 담당합니다.
- **슬레이브 노드**는 **읽기 전용 작업**을 수행하여 시스템의 부하를 효과적으로 분산합니다.
- 대량의 요청이 발생하더라도 **안정적인 성능**을 유지할 수 있습니다.
