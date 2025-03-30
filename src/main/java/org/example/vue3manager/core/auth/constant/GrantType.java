package org.example.vue3manager.core.auth.constant;

/**
 * 枚举类，表示不同的授权类型。
 * 授权类型在 OAuth2 等认证流程中用于指定客户端请求授权的方式。
 *
 * @author aidan.liu
 */
public enum GrantType {

    /**
     * 密码模式，客户端通过用户的用户名和密码请求授权。
     */
    PASSWORD,

    /**
     * 客户端凭证模式，客户端通过自己的客户端ID和客户端密钥请求授权。
     */
    CLIENT_CREDENTIALS,

    /**
     * 刷新令牌模式，客户端通过有效的刷新令牌请求新的访问令牌。
     */
    REFRESH_TOKEN;
}
