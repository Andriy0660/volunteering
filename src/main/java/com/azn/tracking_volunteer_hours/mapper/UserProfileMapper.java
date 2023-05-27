package com.azn.tracking_volunteer_hours.mapper;


import com.azn.tracking_volunteer_hours.dto.response.UserProfileResponse;
import com.azn.tracking_volunteer_hours.entity.User;

public class UserProfileMapper {

    public static UserProfileResponse mapToUserProfile(User user) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setFirstName(user.getFirstname());
        userProfileResponse.setLastName(user.getLastname());

        return userProfileResponse;
    }
}