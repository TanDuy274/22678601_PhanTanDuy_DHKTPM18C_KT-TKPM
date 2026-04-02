# 1. Sử dụng image PHP kèm Apache bản 8.2
FROM php:8.2-apache

# 2. Thiết lập thư mục làm việc mặc định của Apache
WORKDIR /var/www/html

# 3. (Tùy chọn) Cài đặt thêm các extension PHP nếu cần sau này
# RUN docker-php-ext-install mysqli pdo pdo_mysql

# 4. Mở cổng 80 cho web
EXPOSE 80

# Lưu ý: Không cần lệnh CMD vì image gốc đã tự chạy Apache rồi.