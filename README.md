# Hướng dẫn sử dụng dự án JPA Practice

## Yêu cầu hệ thống
- Chống chỉ định MacOS
- JDK 17 trở lên
- Maven
- SQL Server
- IDE (IntelliJ IDEA hoặc Eclipse)

## Cấu hình Database
1. Mở file `src/main/resources/application.properties`
2. Thay đổi các thông tin kết nối database:
   ```properties
   spring.datasource.url=jdbc:sqlserver://YOUR_SERVER_NAME;databaseName=YOUR_DATABASE_NAME;encrypt=true;trustServerCertificate=true
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```
   - Thay `YOUR_SERVER_NAME` bằng tên server SQL của bạn (ví dụ: localhost hoặc tên máy)
   - Thay `YOUR_DATABASE_NAME` bằng tên database bạn muốn sử dụng
   - Thay `YOUR_USERNAME` và `YOUR_PASSWORD` bằng thông tin đăng nhập SQL Server của bạn

## Chạy dự án
1. Mở terminal trong thư mục dự án
2. Chạy lệnh:
   ```bash
   mvn spring-boot:run
   ```
3. Đợi ứng dụng khởi động hoàn tất

Hoặc chỉ cần nhấn "Run" là được.

## Các trang web có sẵn

### 1. Trang đăng ký học sinh (addStudent.html)
- Truy cập: `http://localhost:8080/addStudent.html`
- Chức năng: Thêm học sinh mới
- Các trường thông tin:
  - First Name (Tên)
  - Last Name (Họ)
  - Address (Địa chỉ)
  - Birthday (Ngày sinh)
  - Phone (Số điện thoại)

### 2. Trang thêm khóa học (addCourse.html)
- Truy cập: `http://localhost:8080/addCourse.html`
- Chức năng: Thêm khóa học mới
- Các trường thông tin:
  - Course Name (Tên khóa học)
  - Start Date (Ngày bắt đầu)
  - End Date (Ngày kết thúc)
  - Student ID (ID học sinh)

### 3. Trang tìm kiếm (function.html)
- Truy cập: `http://localhost:8080/function.html`
- Các chức năng tìm kiếm:
  1. **Tìm học sinh theo tên**
     - Nhập tên học sinh vào ô First Name
     - Click nút Search

  2. **Tìm khóa học theo ID học sinh**
     - Nhập ID học sinh vào ô Student ID
     - Click nút Search

  3. **Tìm học sinh trên 20 tuổi**
     - Click nút "Find Students Older Than 20"

  4. **Tìm học sinh trên 20 tuổi theo địa chỉ**
     - Nhập địa chỉ vào ô Address
     - Click nút Search

  5. **Tìm khóa học theo khoảng thời gian**
     - Nhập ngày bắt đầu vào ô Start Date
     - Nhập ngày kết thúc vào ô End Date
     - Click nút Search
  6. **Update và Delete Học sinh và Khóa học**

## API Endpoints

### Student API
1. **Thêm học sinh (2 cách)**
   - POST `/api/students-json` - Thêm học sinh sử dụng JSON
   - POST `/api/students-urlencoded` - Thêm học sinh sử dụng form-urlencoded
     - Parameters: firstName, lastName, address, birthday (YYYY-MM-DD), phone

2. **Tìm kiếm học sinh**
   - GET `/api/students/firstName/{firstName}` - Tìm học sinh theo tên
   - GET `/api/students/older-than-20` - Tìm học sinh trên 20 tuổi
   - GET `/api/students/older-than-20-and-address/{address}` - Tìm học sinh trên 20 tuổi và có địa chỉ cụ thể

### Course API
1. **Thêm khóa học**
   - POST `/api/courses` - Thêm khóa học mới (sử dụng JSON)

2. **Tìm kiếm khóa học**
   - GET `/api/courses/duration` - Tìm khóa học theo khoảng thời gian
     - Parameters: startDate (YYYY-MM-DD), endDate (YYYY-MM-DD)
   - GET `/api/courses/student/{studentId}` - Tìm khóa học theo ID học sinh

## Lưu ý quan trọng
1. Đảm bảo SQL Server đã được cài đặt và đang chạy
2. Database sẽ tự động được tạo khi chạy lần đầu tiên (do cấu hình `spring.jpa.hibernate.ddl-auto = update`)
3. Nếu gặp lỗi kết nối, hãy kiểm tra:
   - SQL Server đã được cài đặt đúng cách
   - Thông tin kết nối trong `application.properties` chính xác
   - SQL Server đang chạy và có thể truy cập được

## Xử lý lỗi thường gặp
1. **Lỗi kết nối database**
   - Kiểm tra SQL Server đã được cài đặt và đang chạy
   - Kiểm tra thông tin kết nối trong `application.properties`
   - Đảm bảo SQL Server cho phép kết nối từ xa (nếu cần)

2. **Lỗi port đang được sử dụng**
   - Kiểm tra xem có ứng dụng nào đang sử dụng port 8080 không
   - Có thể thay đổi port trong `application.properties` bằng cách thêm:
     ```properties
     server.port=8081
     ```

## Hỗ trợ
Nếu bạn gặp bất kỳ vấn đề nào, hãy kiểm tra:
1. Log lỗi trong console
2. Đảm bảo đã cài đặt đầy đủ các dependency trong `pom.xml`
3. Kiểm tra version Java và Maven có tương thích không

