package se.iuh.userService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.iuh.userService.dto.LoginRequest;
import se.iuh.userService.dto.RegisterRequest;
import se.iuh.userService.dto.UserResponse;
import se.iuh.userService.model.User;
import se.iuh.userService.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest req) {
        User user = service.register(req.getUsername(), req.getPassword());
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest req) {
        User user = service.login(req.getUsername(), req.getPassword());
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll().stream()
                .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getRole()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        User u = service.getById(id);
        return new UserResponse(u.getId(), u.getUsername(), u.getRole());
    }
}