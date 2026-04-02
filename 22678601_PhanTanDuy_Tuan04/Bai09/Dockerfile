# 1. Sử dụng redis:latest làm base image
FROM redis:latest

# 2. Thiết lập thư mục làm việc
WORKDIR /usr/local/etc/redis

# 3. Copy file cấu hình từ máy Mac vào container
COPY redis.conf .

# 4. Mở cổng mặc định của Redis
EXPOSE 6379

# 5. Chạy Redis server với đường dẫn file cấu hình cụ thể
# Lưu ý: Lệnh này đè lên lệnh mặc định của image gốc
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]