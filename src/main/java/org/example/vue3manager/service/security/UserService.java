package org.example.vue3manager.service.security;

import org.example.vue3manager.metadata.security.FindCurrentLoginUserInfoResponse;

public interface UserService {

    FindCurrentLoginUserInfoResponse findCurrentLoginUserInfo();
}
