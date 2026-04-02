from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello():
    return "Hello, Docker Flask!"

if __name__ == "__main__":
    # Flask mặc định chạy ở cổng 5000
    # host='0.0.0.0' là bắt buộc để truy cập được từ bên ngoài container
    app.run(host='0.0.0.0', port=5000)