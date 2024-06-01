# HƯỚNG DẪN CÀI ĐẶT VÀ THỰC THI CHƯƠNG TRÌNH

## HƯỚNG DẪN CÀI ĐẶT

### B1: Cài đặt và chạy app FE

- **Cài đặt môi trường NodeJS:**Yêu cầu cài đặt NodeJS 18 hoặc NodeJS 20 tại [trang chủ của NodeJS](https://nodejs.org). Sau đó mở tập tin cài đặt đã tải và làm theo hướng dẫn.

1. Di chuyển vào thư mục `elearning-fe` hoặc sử dụng command với terminal

```bash
cd elearning-fe
```

2. Thực hiện cài đặt các thư viện cần thiết với các câu lệnh

```bash
npm install
# hoặc
yarn
```

3. Chạy chương trình

```bash
npm run dev
# hoặc
yarn dev
```

- **App đã chạy trên port 3000**: Có thể bật trình duyệt tại localhost:3000 để chạy

### B2: Cài đặt chương trình BE:

#### Cách 1: SỬ DỤNG VỚI DOCKER, ĐOCKER DESKTOP

- **Cài đặt môi trường**: Khuyến cáo sử dụng với Docker. Cài đặt Docker Desktop tại [đây](https://www.docker.com/products/docker-desktop/)

1. Di chuyển vào thư mục `ELearningBE/Elearning` bằng câu lệnh:

```bash
cd ELearningBE/Elearning
```

2. Chạy chương trình với câu lệnh:

```bash
docker compose -f compose.dev.yml up --build
```

- Vui lòng chờ để docker thực thi build image và start

#### Cách 2: CHẠY BÌNH THƯỜNG

- Môi trường cần được chuẩn bị sẵn:

* JAVA Development Kit 17.
* Biến môi trường JAVA_HOME.
* MYSQL với tên database: `elearning`
* Redis

- Chạy file `ELearningBE/db_log/elearning_2024-05-17.sql` trong MySQL để sinh data trong database

1. Di chuyển vào thư mục `ELearningBE/Elearning` bằng câu lệnh:

```bash
cd ELearningBE/Elearning
```

2. Chạy chương trình:

```bash
./mvnw spring-boot:run
```

### Cả 2 cách trên

- **Chương trình đã chạy trên port 8080**

## THỰC THI CHƯƠNG TRÌNH

#### TÀI KHOẢN ADMIN:

- Chương trình chạy trên: `localhost:3000`
  Đây là tài khoản người dùng có quyền thực thi toàn bộ chương trình

- username: admin
- password: 123456
