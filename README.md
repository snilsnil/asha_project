### Asha Project
Asha Project는 온라인 경매 플랫폼을 위한 혁신적인 시스템입니다. 판매자와 구매자가 실시간으로 소통하며, 안전하고 원활하게 경매를 진행할 수 있도록 설계되었습니다. 이 프로젝트는 RESTful API 설계를 바탕으로 생성자 패턴을 활용하여 확장성 높은 시스템을 구축하였습니다.

---

### 🚀 Asha Project란?
Asha Project는 온라인 경매 관리 시스템을 위한 플랫폼으로, 사용자 경험을 최우선으로 생각하며 경매에 필요한 기능을 제공합니다. 사용자는 경매에 참여하거나 상품을 등록하고 판매할 수 있으며, 경매 진행 상태를 실시간으로 확인할 수 있습니다. 또한, 빌더 패턴을 활용하여 객체 생성을 유연하게 처리하고, RESTful API를 통해 클라이언트와의 효율적인 데이터 교환을 구현하였습니다.

---

### ⚙️ 기술 스택
Backend:

Spring Boot: 강력하고 확장 가능한 RESTful API 서버 구축
JPA/Hibernate: 데이터베이스와의 객체 관계 매핑
MySQL: 데이터 저장을 위한 관계형 데이터베이스
Frontend:

Next.js: React 기반의 서버 사이드 렌더링(SSR) 및 정적 사이트 생성(SSG) 기능 제공
Gradle: 빌드 도구 및 의존성 관리

빌더 패턴: 객체 생성을 유연하게 관리하기 위해 사용
RESTful API: 클라이언트와 서버 간의 효율적인 데이터 전송을 위한 설계
Spring Security: 인증 및 권한 관리

---

### 🎯 핵심 기능
상품 등록 및 경매 시작: 판매자는 상품을 등록하고, 즉시 구매 가격 및 시작 가격을 설정할 수 있습니다.

실시간 경매: 경매가 진행되는 동안 실시간으로 입찰가를 갱신하며, 최종 입찰자를 선정합니다.

사용자 관리: 사용자는 본인의 입찰 내역과 경매 참여 기록을 관리할 수 있습니다.

카테고리 기반 상품 검색: 다양한 카테고리로 상품을 검색하고 필터링할 수 있습니다.

🛠 빌드 및 실행
백엔드 실행:

Gradle을 통해 백엔드 서버를 실행합니다.
./gradlew Run 
프론트엔드 실행:

npm install 
npm run


