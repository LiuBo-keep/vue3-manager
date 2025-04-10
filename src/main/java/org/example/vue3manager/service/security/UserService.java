package org.example.vue3manager.service.security;

import org.example.vue3manager.common.exception.BusinessException;
import org.example.vue3manager.metadata.security.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.metadata.security.UserCreateRequest;

public interface UserService {

    FindCurrentLoginUserInfoResponse findCurrentLoginUserInfo();

    void createUser(UserCreateRequest userCreateRequest) throws BusinessException;

    void updateUser();
}
