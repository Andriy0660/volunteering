package com.azn.tracking_volunteer_hours.mapper;

import java.util.List;

public class ReportMapper {
    public static ReportResponse mapToReportResponse(List<ProjectDTO> project, Integer hours) {
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setProject(project);
        reportResponse.setHours(hours);
        return reportResponse;
    }
}
