package org.example.vue3manager.metadata.security;

import lombok.Data;

@Data
public class FindCurrentLoginUserInfoResponse {

    private String name;

    private String email;

    private String phone;
}
