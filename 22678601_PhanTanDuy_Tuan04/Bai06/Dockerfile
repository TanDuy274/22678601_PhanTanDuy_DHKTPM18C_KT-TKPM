# GIAI ĐOẠN 1: BUILDER
FROM node:18-slim AS builder

WORKDIR /app

# Copy file định nghĩa thư viện
COPY package*.json ./

# Cài đặt tất cả thư viện (bao gồm cả devDependencies nếu có)
RUN npm install

# Copy mã nguồn
COPY . .

# Xóa các thư viện không cần thiết cho môi trường Production (tùy chọn nhưng nên có)
RUN npm prune --production


# GIAI ĐOẠN 2: RUNNER (STAGE CHẠY)
FROM node:18-alpine

WORKDIR /app

# CHỈ copy node_modules đã tối ưu và file code từ stage builder sang
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/app.js ./app.js
COPY --from=builder /app/package.json ./package.json

# Thiết lập biến môi trường là Production
ENV NODE_ENV=production

EXPOSE 3000

CMD ["node", "app.js"]