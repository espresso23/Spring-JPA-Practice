# Hướng dẫn sử dụng dự án JPA Practice

## Yêu cầu hệ thống
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
 Chỉ cần nhấn "Run" là được.

# Hướng Dẫn Nhanh Hệ Thống Đăng Ký Làm Thêm

## Truy cập nhanh
🌐 **Địa chỉ cơ bản**: `http://localhost:8080/spmvc`

## Các đường dẫn chính

### 1. Trang chủ đăng ký
🔗 **URL**: `/spmvc/dangky`  
📌 **Chức năng**:
- Đăng ký làm thêm mới
- Xem danh sách đăng ký
- Tìm kiếm, sửa, xóa đăng ký

### 2. Quản lý việc làm
🔗 **URL**: `/spmvc/vieclam`  
📌 **Chức năng**:
- Thêm công việc mới
- Xem danh sách công việc

## Cách sử dụng cơ bản

1. **Đăng ký làm thêm**:
    - Vào `/spmvc/dangky`
    - Điền form và nhấn "Lưu"

2. **Thêm việc mới**:
    - Vào `/spmvc/vieclam`
    - Điền form và nhấn "Lưu"

3. **Tìm kiếm**:
    - Trên trang `/spmvc/dangky`
    - Nhập từ khóa → chọn loại tìm → nhấn "Tìm kiếm"

## Mẹo nhớ URL
- `spmvc/dangky` → **Đăng ký**
- `spmvc/vieclam` → **Việc làm**

> 💡 Luôn bắt đầu với `/spmvc/` trước các đường dẫn trên!