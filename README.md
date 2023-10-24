# ELearningBE

Graduation project

## HỆ THỐNG HỖ TRỢ HỌC TẬP TRỰC TUYẾN

## Công nghệ sử dụng

- Phía backend: `Java Spring Boot`
- Phía cơ sở dữ liệu: `MySQL`

## Hướng dẫn cài đặt

### Bước 1: Yêu cầu môi trường cài đặt (vui lòng bỏ qua nếu đã đáp ứng các môi trường bên dưới).

- **Cài đặt JDK 17:** tải và cài đặt JDK 17 [tại đây](https://download.oracle.com/java/17/archive/jdk-17.0.8_windows-x64_bin.exe "sha256 "). Sau đó mở tập tin cài đặt đã tải và làm theo hướng dẫn.
- **Cài đặt MySQL Community Server:** tải và cài đặt MySQL Community Server [tại đây](https://dev.mysql.com/get/Downloads/MySQL-8.1/mysql-8.1.0-winx64.msi). Sau đó mở tập tin cài đặt đã tải và làm theo hướng dẫn.
- **Cài đặt MySQL Workbench:** tải và cài đặt MySQL Workbench [tại đây](https://dev.mysql.com/get/Downloads/MySQLGUITools mysql-workbench-community-8.0.34-winx64.msi). Sau đó mở tập tin cài đặt đã tải và làm theo hướng dẫn.
- **Cài đặt IntelliJ IDEA Community:** vào [trang chủ của JetBrains](https://www.jetbrains.com/idea/download/?section=windows) và tải phiên bản IntelliJ IDEA Commnunity.

### Bước 2: Mở `command line` hoặc `git bash` và gõ lệnh sau để tải mã nguồn đồ án về máy tính.

```bash
git clone https://github.com/dauxuanhoanghung/ELearningBE.git
```

### Bước 3: Mở **MySQL Workbench** và tạo mới một schema đặt tên là `elearning`. Sau đó nhập dữ liệu từ tập tin `db.sql`

Các tài khoản đều có password là 123456.

### Bước 4: Với thư mục `server` đã mở với **IntelliJ IDEA Community**, tạo tập tin `application.properties` với tập tin `application_template.properties` sửa lại các chi tiết. Thêm `client_secret.json` lấy từ google console

```properties
spring.datasource.username=<Tên đăng nhập MySQL>
spring.datasource.password=<Mật khẩu đăng nhập MySQL>
```

```properties
aws.access.key.id=
aws.secret.access.key=
aws.s3.region=
aws.s3.bucket.name=
```

```properties
cloudinary.cloud_name=
cloudinary.api_key=
cloudinary.secret_key=
```

Sau khi chỉnh sửa, mở tập tin `ELearningApplication.java` và chạy tập tin này. Ứng dụng Java sẽ được chạy trên `http://localhost:8080/`.

### Frontend code

[Source](https://github.com/dauxuanhoanghung/elearning-fe.git)
