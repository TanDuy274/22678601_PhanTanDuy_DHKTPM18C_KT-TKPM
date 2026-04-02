# 1. Sử dụng node:18 làm base image như yêu cầu
FROM node:18

# 2. Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# 3. Sao chép file app.js từ máy tính vào thư mục /app trong container
COPY app.js .

# 4. Mở cổng 3000 để có thể truy cập từ bên ngoài
EXPOSE 3000

# 5. Lệnh để chạy ứng dụng khi container khởi động
CMD ["node", "app.js"]