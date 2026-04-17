package edu.iuh.fit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua nếu User Service gửi dư field
public class UserRegisteredEvent {
    // Sửa tên biến cho khớp 100% với UserEventDTO của User Service
    private Long id;
    private String userName; // Lưu ý: chữ N viết hoa giống bên User Service
    private String email;
    // Field thứ 4 là String action ("USER_REGISTERED") bị dư ra,
    // nhưng nhờ có @JsonIgnoreProperties nó sẽ tự động bỏ qua mà không báo lỗi.
}