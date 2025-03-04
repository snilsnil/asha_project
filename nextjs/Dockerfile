# 1. Node.js 이미지 사용
FROM node:22.11

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. package.json과 package-lock.json 복사
COPY package*.json ./

# 4. Next.js 의존성 설치
RUN npm install

# 5. 모든 파일 복사
COPY . .

# 6. Next.js 빌드
RUN npm run build:docker

# 7. 컨테이너에서 3000번 포트 사용
EXPOSE 3000

# 8. Next.js 실행
CMD ["npm", "run", "start:docker"]
