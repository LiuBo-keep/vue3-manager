package org.example.vue3manager.common.auth;


/**
 * 认证类型，例如Bearer或Basic等.
 *
 * @author aidan.liu
 */
public enum AuthType {

  BEARER("Bearer"),

  BASIC("Basic");

  private final String type;

  AuthType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
