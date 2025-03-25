# Hướng dẫn sử dụng dự án JPA Practice

## Yêu cầu hệ thống
- JDK 17 trở lên
- Maven
- SQL Server
- IDE (IntelliJ IDEA hoặc Eclipse)

## Cấu hình Database
1. Mở file `src/main/resources/application.properties`
2. Thay đổi các thông tin kết nối database: dưới đây là mẫu
   ```properties
    server.servlet.context-path=/spmvc
    server.port=8080
    spring.datasource.url=jdbc:sqlserver://PLQTAN\\SQLEXPRESS;databaseName=testSPD;encrypt=true;trustServerCertificate=true
    spring.datasource.username=sa
    spring.datasource.password=123
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql = true
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    ## Hibernate Properties
    # The SQL dialect makes Hibernate generate better SQL for the chosen database
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.SQLServer2012Dialect

    # Hibernate ddl auto (create, create-drop, validate, update)
    spring.jpa.hibernate.ddl-auto = update
    # DEV Tool
    spring.devtools.restart.enabled=true
    spring.devtools.livereload.enabled=true
    # Hidden suffix
    spring.thymeleaf.prefix=classpath:/templates/
    spring.thymeleaf.suffix=.html
    spring.thymeleaf.mode=HTML5
   ```
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