package org.example.vue3manager.metadata.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthResponse类用于封装认证响应信息
 * 它主要包含认证类型、认证令牌和令牌过期时间等信息
 *
 * @author aidan.liu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    /**
     * 认证类型，例如Bearer或Basic等
     */
    private String type;

    /**
     * 认证令牌，用于访问受保护的资源
     */
    private String token;

    /**
     * 令牌过期时间，通常以时间戳的形式表示
     */
    private long expires_in;

    /**
     * Token刷新凭证。用于在token过期时，进行刷新。也可以通过重新获取 Token 处理
     */
    private String refresh_token;

    /**
     * Token刷新凭证有效期
     */
    private long refresh_expires_in;
}
