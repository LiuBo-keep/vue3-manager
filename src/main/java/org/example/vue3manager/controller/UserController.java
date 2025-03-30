package org.example.vue3manager.controller;

import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.metadata.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current-login-user")
    public Response getCurrentLoginUser() {
        FindCurrentLoginUserInfoResponse currentLoginUserInfo = userService.findCurrentLoginUserInfo();
        return Response.ok().entry(currentLoginUserInfo).build();
    }
}
