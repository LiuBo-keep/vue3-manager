package org.example.vue3manager.metadata.security;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FindCurrentLoginUserInfoResponse {
    private String name;
    private String email;
    private String phone;
    private LocalDateTime passwordExpireTime;
    private Boolean temporaryPassword;
    private String userType;
    private String status;
    private String avatarPath;
}
