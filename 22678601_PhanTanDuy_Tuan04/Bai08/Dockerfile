# 1. Sử dụng postgres:15 làm base image theo yêu cầu
FROM postgres:15

# 2. Thiết lập các biến môi trường bắt buộc cho Postgres
# (Duy có thể thay đổi mật khẩu tùy ý)
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword
ENV POSTGRES_DB=mydatabase

# 3. Copy file init.sql vào thư mục đặc biệt của Postgres
# Đây là "vị trí thần thánh" để Postgres tự động chạy script khi init
COPY init.sql /docker-entrypoint-initdb.d/

# 4. Postgres mặc định chạy trên cổng 5432
EXPOSE 5432