package org.example.vue3manager.service.impl;

import org.example.vue3manager.core.auth.context.SecurityIdentity;
import org.example.vue3manager.metadata.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final SecurityIdentity securityIdentity;

    public UserServiceImpl(SecurityIdentity securityIdentity) {
        this.securityIdentity = securityIdentity;
    }

    @Override
    public FindCurrentLoginUserInfoResponse findCurrentLoginUserInfo() {
        String username = securityIdentity.getUsername();
        FindCurrentLoginUserInfoResponse response = new FindCurrentLoginUserInfoResponse();
        response.setName(username);
        return response;
    }
}
