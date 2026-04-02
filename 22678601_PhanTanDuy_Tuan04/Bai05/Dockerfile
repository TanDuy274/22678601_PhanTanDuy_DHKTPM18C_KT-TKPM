# GIAI ĐOẠN 1: Build (Sử dụng image golang đầy đủ)
FROM golang:1.21-alpine AS builder

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy các file quản lý thư viện và tải về trước
COPY go.mod ./
RUN go mod download

# Copy toàn bộ mã nguồn và biên dịch thành file 'main'
COPY . .
RUN go build -o main .

# GIAI ĐOẠN 2: Run (Sử dụng image alpine siêu nhẹ để chạy)
FROM alpine:latest

WORKDIR /root/

# Chỉ copy file thực thi đã biên dịch từ giai đoạn 'builder' sang
COPY --from=builder /app/main .

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng
CMD ["./main"]