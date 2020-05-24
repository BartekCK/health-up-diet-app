package com.dietician.server.controllers;

import com.dietician.server.db.entities.User;
import com.dietician.server.dtos.requests.UserGoalDataRequest;
import com.dietician.server.dtos.responses.UserGoalDataResponse;
import com.dietician.server.dtos.responses.UserResponse;
import com.dietician.server.services.UserDataService;
import com.dietician.server.services.UserService;
import com.dietician.server.utilities.converters.UserConverter;
import com.dietician.server.utilities.converters.UserDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final UserDataService userDataService;
    private final UserDataConverter userDataConverter;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(
                userService.getUsers().stream()
                        .map(userConverter::convertToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(Authentication authentication) {
        String userRole = userService.getUserRole(getUsernameFromAuthentication(authentication));
        return ResponseEntity.ok(userRole);
    }

    @PostMapping("/data")
    public ResponseEntity<Void> addUserData(Authentication authentication, @RequestBody @Valid UserGoalDataRequest request) {
        userDataService.addUserData(getUsernameFromAuthentication(authentication), userDataConverter.convertToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data")
    public ResponseEntity<UserGoalDataResponse> getUserData(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserGoalData(getUsernameFromAuthentication(authentication)));
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }
}
