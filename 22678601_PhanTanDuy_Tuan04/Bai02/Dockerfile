# 1. Sử dụng python:3.9 làm base image theo yêu cầu
FROM python:3.9

# 2. Thiết lập thư mục làm việc trong container
WORKDIR /app

# 3. Sao chép file requirements.txt vào trước để tối ưu cache
COPY requirements.txt .

# 4. Cài đặt các thư viện cần thiết (Flask)
RUN pip install --no-cache-dir -r requirements.txt

# 5. Sao chép toàn bộ code còn lại vào container
COPY . .

# 6. Mở cổng 5000 (cổng mặc định của Flask)
EXPOSE 5000

# 7. Lệnh khởi chạy ứng dụng
CMD ["python", "app.py"]