package com.azn.tracking_volunteer_hours.mapper;

import com.azn.tracking_volunteer_hours.dto.response.ReportResponse;
import com.azn.tracking_volunteer_hours.dto.response.UserProfileResponse;
import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;

import java.util.List;

public class ReportMapper {
    public static ReportResponse mapToReportResponse(List<Project> project, Integer hours) {
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setProject(project);
        reportResponse.setHours(hours);
        return reportResponse;
    }
}