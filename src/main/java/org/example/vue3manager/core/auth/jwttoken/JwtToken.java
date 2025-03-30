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

    private Map<String, Object> header;

    private Map<String, Object> payload;

    private String signature;


    public String getSubject() {
        return payload.get("sub").toString();
    }
}
