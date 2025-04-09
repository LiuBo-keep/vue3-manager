package org.example.vue3manager.controller.security;

import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.metadata.security.AuthRequest;
import org.example.vue3manager.metadata.security.AuthResponse;
import org.example.vue3manager.service.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/token")
  public Response auth(@RequestBody AuthRequest authRequest) throws AuthException {
    AuthResponse authResponse = authService.auth(authRequest);
    return Response.ok().entry(authResponse).build();
  }
}
