package org.example.vue3manager.core.auth.constant;

public class AuthFlag {

    public static final String authHeader = "Authorization";

    /**
     * 访问令牌的过期时间，单位为秒（1小时）
     */
    public static final long ACCESS_TOKEN_EXPIRATION = 3600;
    /**
     * 刷新令牌的过期时间，单位为秒（7天）
     */
    public static final long REFRESH_TOKEN_EXPIRATION = 86400 * 7;
}
