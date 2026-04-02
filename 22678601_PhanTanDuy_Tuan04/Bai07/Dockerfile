# 1. Sử dụng image python:3.9-slim cho nhẹ
FROM python:3.9-slim

# 2. Thiết lập thư mục làm việc
WORKDIR /app

# 3. Thiết lập biến môi trường mặc định theo yêu cầu
ENV APP_ENV=development

# 4. Copy file code vào container
COPY app.py .

# 5. Chạy ứng dụng
CMD ["python", "app.py"]