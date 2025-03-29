package org.example.vue3manager.core.auth.constant;


import lombok.Getter;

/**
 * 认证类型，例如Bearer或Basic等.
 *
 * @author aidan.liu
 */
@Getter
public enum AuthType {

  BEARER("Bearer"),

  BASIC("Basic");

  private final String type;

  AuthType(String type) {
    this.type = type;
  }
}
