package org.example.vue3manager.core.auth.jwttoken;

import lombok.Data;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

/**
 * 表示 JSON Web Token (JWT) 的类，用于处理和存储 JWT 信息
 *
 * @author aidan.liu
 */
@Data
@RequestScope
public class JwtToken {

    /**
     * 完整的 JWT 字符串
     */
    private String token;

    /**
     * JWT 中的主题（subject），通常表示用户标识
     */
    private String subject;

    private Map<String, Object> header;

    private Map<String, Object> payload;

    private String signature;
}
