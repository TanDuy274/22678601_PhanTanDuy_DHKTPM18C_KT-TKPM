-- Tạo bảng người dùng đơn giản
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Chèn một vài dữ liệu mẫu
INSERT INTO users (username, email) VALUES 
('duyphan', 'duy.phan@student.iuh.edu.vn'),
('docker_admin', 'admin@docker.io');