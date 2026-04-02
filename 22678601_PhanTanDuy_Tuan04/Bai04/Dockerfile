# 1. Sử dụng nginx:latest làm base image
FROM nginx:latest

# 2. (Tùy chọn) Thiết lập thư mục làm việc, nhưng Nginx mặc định dùng đường dẫn bên dưới
# Chúng ta sẽ copy file index.html vào đúng thư mục mà Nginx phục vụ web
COPY index.html /usr/share/nginx/html/index.html

# 3. Mở cổng 80 (Cổng mặc định của HTTP và Nginx)
EXPOSE 80

# Lưu ý: Nginx image chính thức đã có sẵn lệnh khởi chạy (CMD), 
# nên chúng ta không nhất thiết phải viết thêm CMD ["nginx", "-g", "daemon off;"]