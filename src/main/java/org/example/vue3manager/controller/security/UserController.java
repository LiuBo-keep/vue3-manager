package org.example.vue3manager.controller.security;

import jakarta.validation.Valid;
import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.core.auth.annotation.Authenticated;
import org.example.vue3manager.metadata.security.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.metadata.security.UserCreateRequest;
import org.example.vue3manager.metadata.security.UserUpdateRequest;
import org.example.vue3manager.service.security.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Authenticated
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

  @GetMapping("/page")
  public Response getUser() {
    return Response.ok().build();
  }

  @GetMapping("/detail/{id}")
  public Response getUserById(@PathVariable("id") String id) {
    return Response.ok().build();
  }

  @PostMapping()
  public Response createUser(
      @Valid @RequestBody UserCreateRequest userCreateRequest) {
    return Response.ok().build();
  }

  @PutMapping("/{id}")
  public Response updateUser(
      @PathVariable("id") String id,
      @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
    return Response.ok().build();
  }

  @DeleteMapping("/{id}")
  public Response deleteUser(
      @PathVariable("id") String id) {
    return Response.ok().build();
  }
}
