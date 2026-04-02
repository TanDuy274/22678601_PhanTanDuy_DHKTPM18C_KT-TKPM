# 1. Sử dụng node:18-alpine làm base image (siêu nhẹ)
FROM node:18-alpine

# 2. Thiết lập thư mục làm việc
WORKDIR /app

# 3. Copy file quản lý thư viện vào trước để tận dụng cache
COPY package.json package-lock.json ./

# 4. Cài đặt các dependencies (thư viện)
RUN npm install

# 5. Copy toàn bộ mã nguồn vào container
COPY . .

# 6. Mở cổng 3000 (cổng mặc định của React)
EXPOSE 3000

# 7. Lệnh khởi chạy ứng dụng ở chế độ Development
CMD ["npm", "start"]