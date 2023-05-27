package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.dto.response.UserProfileResponse;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    @GetMapping()
    public ResponseEntity<UserProfileResponse> profile(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return ResponseEntity.ok(UserProfileMapper.mapToUserProfile(user));
    }
}
